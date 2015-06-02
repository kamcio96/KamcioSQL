package pl.kamcio96.kamciosql;

import com.google.common.base.Preconditions;
import pl.kamcio96.kamciosql.query.impl.PreparedQuery;
import pl.kamcio96.kamciosql.query.impl.QueryResultSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Database {

    public static Database newMySQL(TaskProvider provider, String hostline, String database, String username, String password) {
        return new MySQL(provider, hostline, database, username, password);
    }

    //TODO sqlite

    /*=========================================================*/

    private final AtomicReference<Connection> connection = new AtomicReference<>();
    private final AtomicBoolean active = new AtomicBoolean(false);
    private final AtomicBoolean debug = new AtomicBoolean(false);
    protected final TaskProvider provider;

    public Database(TaskProvider provider) {
        this.provider = provider;
    }

    protected abstract Connection connect() throws Exception;

    protected Connection getConnection() {
        return connection.get();
    }

    protected final void checkConnection() {
        try {
            Connection c = connection.get();
            if(c == null || c.isClosed()){
                connection.set(connect());
                active.set(true);
            }
        } catch (Exception e){
            throw new RuntimeException("Exception during connecting to database!", e);
        }
    }

    public final void runQuery(final PreparedQuery query) {
        Preconditions.checkState(active.get(), "Database closed!");

        Runnable runnable = new QueryRunner(query);

        if(query.isNow()) {
            runnable.run();
        } else {
            provider.runTask(runnable);
        }
    }

    public void setDebug(boolean debug) {
        this.debug.set(debug);
    }

    public boolean isClosed() {
        return active.get();
    }

    public final void close() {
        Preconditions.checkState(active.get(), "Database already closed!");

        try {
            connection.getAndSet(null).close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception during closing database connection!", e);
        }
    }

    public abstract Type getType();

    private class QueryRunner implements Runnable {

        private final PreparedQuery query;

        private QueryRunner(PreparedQuery query) {
            this.query = query;
        }

        @Override public void run() {
            try {
                checkConnection();
                Statement statement = connection.get().createStatement();
                if(debug.get()) provider.getLogger().info("[DEBUG]: Run SQL query: \"" + query.getQuery() + "\"" );
                ResultSet rs = query.getExecutor().runQuery(statement, query.getQuery());
                if(rs != null && query.getCallback() != null) {
                    try {
                        query.getCallback().done(new QueryResultSet(rs));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                if(query.getCallback() != null) {
                    query.getCallback().error(e);
                }
            }
       }
    }

    public enum Type {
        SQLITE,
        MYSQL
    }
}
