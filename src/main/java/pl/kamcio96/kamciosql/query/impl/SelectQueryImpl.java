package pl.kamcio96.kamciosql.query.impl;

import com.google.common.base.Preconditions;
import pl.kamcio96.kamciosql.Database;
import pl.kamcio96.kamciosql.QueryExecutor;
import pl.kamcio96.kamciosql.condition.Condition;
import pl.kamcio96.kamciosql.condition.ConditionBuilder;
import pl.kamcio96.kamciosql.query.SelectQuery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SelectQueryImpl extends QueryImpl<SelectQuery> implements SelectQuery {

    private Set<String> columns = new HashSet<>();
    private LimitModule limitModule = new LimitModule();
    private String whereCondition;

    @Override public SelectQuery column(String... column) {
        columns.addAll(Arrays.asList(column));
        return this;
    }

    @Override public void run(Database database) {
        Preconditions.checkNotNull(table, "table");

        StringBuilder builder = new StringBuilder("SELECT ");

        if (columns.size() > 0) {
            boolean first = true;
            for (String column : columns) {
                if (!first)
                    builder.append(", ");
                builder.append("`").append(column).append("`");
                first = false;
            }
        } else {
            builder.append("*");
        }

        builder.append(" FROM `").append(table).append("`");

        if(whereCondition != null) {
            builder.append(" WHERE ").append(whereCondition);
        }

        builder.append(limitModule.getQueryPart());

        //TODO order
        database.runQuery(new PreparedQuery(builder.toString(), now, callback, QueryExecutor.SELECT));
    }

    @Override public SelectQuery limit(int limit) {
        limitModule.limit(limit);
        return this;
    }

    @Override public SelectQuery offset(int offset) {
        limitModule.offset(offset);
        return this;
    }

    @Override public SelectQuery where(Condition condition) {
        whereCondition = condition.toString();
        return this;
    }

    @Override public SelectQuery where(ConditionBuilder condition) {
        whereCondition = condition.toString();
        return this;
    }

    @Override public SelectQuery clone() {
        SelectQueryImpl copy = new SelectQueryImpl();
        copySuper(copy);
        copy.columns = new HashSet<>(columns);
        copy.limitModule = limitModule.clone();
        copy.whereCondition = whereCondition;
        return copy;
    }
}
