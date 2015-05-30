package pl.kamcio96.kamciosql;

import pl.kamcio96.kamciosql.query.impl.PreparedQuery;
import pl.kamcio96.kamciosql.query.impl.QueryResultSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Database {

    private Connection connection;
    private AtomicBoolean active = new AtomicBoolean(false);

    protected final TaskProvider provider;

    public Database(TaskProvider provider) {
        this.provider = provider;
    }

    protected abstract Connection connect();

    public abstract Type getType();

    public void runQuery(final PreparedQuery query) {

        Runnable runnable = new Runnable() {
            @Override public void run() {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = query.getExecutor().runQuery(statement, query.getQuery());
                    if(rs != null && query.getCallback() != null) {
                        query.getCallback().done(new QueryResultSet(rs));
                    }
                    //TODO callback
                } catch (Exception e) {
                    if(query.getCallback() != null) {
                        query.getCallback().error(e);
                    }
                }
            }
        };

        if(query.isNow()) {
            runnable.run();
        } else {
            provider.runTask(runnable);
        }
    }

    public enum Type {
        SQLITE,
        MYSQL
    }
}
