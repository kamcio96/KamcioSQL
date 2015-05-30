package pl.kamcio96.kamciosql.query.impl;

import pl.kamcio96.kamciosql.query.QueryResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultSet implements QueryResult {

    private ResultSet resultSet;

    public QueryResultSet(java.sql.ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override public boolean next() throws SQLException {
        return resultSet.next();
    }

    @Override public byte getByte(String column) throws SQLException{
        return resultSet.getByte(column);
    }

    @Override public int getInt(String column) throws SQLException {
        return resultSet.getInt(column);
    }

    @Override public long getLong(String column) throws SQLException {
        return resultSet.getLong(column);
    }

    @Override public float getFloat(String column) throws SQLException {
        return (float) getDouble(column);
    }

    @Override public double getDouble(String column) throws SQLException {
        return resultSet.getLong(column);
    }

    @Override public String getString(String column) throws SQLException {
        return resultSet.getString(column);
    }

}
