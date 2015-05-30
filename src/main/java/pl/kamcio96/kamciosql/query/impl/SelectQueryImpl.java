package pl.kamcio96.kamciosql.query.impl;

import com.google.common.base.Preconditions;
import pl.kamcio96.kamciosql.Database;
import pl.kamcio96.kamciosql.QueryExecutor;
import pl.kamcio96.kamciosql.query.SelectQuery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SelectQueryImpl extends QueryImpl<SelectQuery> implements SelectQuery {

    private Set<String> columns = new HashSet<>();

    @Override public SelectQuery culumn(String... column) {
        columns.addAll(Arrays.asList(column));
        return this;
    }

    @Override public void run(Database database) {
        Preconditions.checkNotNull(table, "table");

        StringBuilder builder = new StringBuilder("SELECT ");

        columns: {
            if(columns.size() > 0) {
                boolean first = true;
                for(String column : columns) {
                    if(first) builder.append(", ");
                    builder.append("`").append(column).append("`");
                    first = false;
                }
            } else {
                builder.append("*");
            }
        }

        builder.append(" FROM `").append(table).append("`");

        //TODO where
        //TODO limit
        //TODO order
        database.runQuery(new PreparedQuery(builder.toString(), now, callback, QueryExecutor.SELECT));
    }

}
