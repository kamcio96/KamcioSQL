package pl.kamcio96.kamciosql.query;

import pl.kamcio96.kamciosql.Database;

public interface Query<T extends Query> {

    public T table(String table);

    public T now(boolean now);

    public T callback(QueryCallback callback);

    //public T clone();

    public void run(Database database);

}
