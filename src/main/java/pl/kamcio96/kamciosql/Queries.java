package pl.kamcio96.kamciosql;

import pl.kamcio96.kamciosql.query.SelectQuery;
import pl.kamcio96.kamciosql.query.impl.SelectQueryImpl;

public class Queries {

    private Queries() { }

    public static SelectQuery select() {
        return new SelectQueryImpl();
    }

}
