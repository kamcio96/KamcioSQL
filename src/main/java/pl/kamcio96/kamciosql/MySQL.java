package pl.kamcio96.kamciosql;

import java.sql.Connection;
import java.sql.DriverManager;

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
        return DriverManager.getConnection(url, username, password);
    }

    @Override public Type getType() {
        return Type.MYSQL;
    }
}
