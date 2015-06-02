package pl.kamcio96.kamciosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

class MySQL extends Database {

    private String url;
    private String username;
    private String password;

    public MySQL(TaskProvider provider, String hostline, String database, String username, String password) {
        super(provider);
        this.url = "jdbc:mysql://" + hostline +"/" + database;
        this.username = username;
        this.password = password;
        checkConnection();
    }

    @Override protected Connection connect() throws Exception {
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }

    @Override public Type getType() {
        return Type.MYSQL;
    }

    private class KeepAliveTask implements Runnable {

        @Override public void run() {
            try {
                checkConnection();
                getConnection().createStatement().executeUpdate("DO 1");
            } catch (SQLException e) {
                provider.getLogger().log(Level.WARNING, "Exception during keep alive");
            }
        }
    }
}
