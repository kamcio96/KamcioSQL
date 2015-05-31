package pl.kamcio96.kamciosql;

import pl.kamcio96.kamciosql.query.SelectQuery;
import pl.kamcio96.kamciosql.query.UpdateQuery;
import pl.kamcio96.kamciosql.query.impl.SelectQueryImpl;
import pl.kamcio96.kamciosql.query.impl.UpdateQueryImpl;

public class Queries {

    private Queries() { }

    public static SelectQuery select() {
        return new SelectQueryImpl();
    }

    public static UpdateQuery update() {
        return new UpdateQueryImpl();
    }

    //public static InsertQuery insert() {
    //    return new InsertQueryImpl();
    //}

}
