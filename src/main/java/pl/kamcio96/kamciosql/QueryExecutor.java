package pl.kamcio96.kamciosql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public enum QueryExecutor {

    SELECT {
        @Override ResultSet runQuery(Statement statement, String query) throws SQLException {
            return statement.executeQuery(query);
        }
    },
    UPDATE {
        @Override ResultSet runQuery(Statement statement, String query) throws SQLException {
            statement.executeUpdate(query);
            return statement.getGeneratedKeys();
        }
    };

    abstract ResultSet runQuery(Statement statement, String query) throws SQLException;
}
