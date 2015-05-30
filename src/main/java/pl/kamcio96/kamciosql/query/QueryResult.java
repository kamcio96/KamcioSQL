package pl.kamcio96.kamciosql.query;

import java.sql.SQLException;

public interface QueryResult {

    public boolean next() throws SQLException;

    public byte getByte(String column) throws SQLException;

    public int getInt(String column) throws SQLException;

    public long getLong(String column) throws SQLException;

    public float getFloat(String column) throws SQLException;

    public double getDouble(String column) throws SQLException;

    public String getString(String column) throws SQLException;

}
