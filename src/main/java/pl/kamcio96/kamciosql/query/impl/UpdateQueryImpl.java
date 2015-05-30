package pl.kamcio96.kamciosql.query.impl;

import com.google.common.base.Preconditions;
import pl.kamcio96.kamciosql.Database;
import pl.kamcio96.kamciosql.QueryExecutor;
import pl.kamcio96.kamciosql.condition.ConditionBuilder;
import pl.kamcio96.kamciosql.query.UpdateQuery;

import java.util.HashMap;
import java.util.Map;

public class UpdateQueryImpl extends QueryImpl<UpdateQuery> implements UpdateQuery {

    private Map<String, String> data = new HashMap<String, String>();
    private LimitModule limitModule = new LimitModule();
    private String whereCondition;

    @Override public void add(String column, String value) {
        data.put(column, value);
    }

    @Override public void run(Database database) {
        Preconditions.checkNotNull(table, "table");
        Preconditions.checkState(data.size() > 0, "Empty data");

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE `").append(table).append("` ");
        boolean firstData = true;
        for(Map.Entry<String, String> entry : data.entrySet()) {
            if(!firstData) {
                builder.append(", ");
            }
            builder.append("`").append(entry.getKey()).append("`");
            builder.append("=");
            builder.append("'").append(entry.getValue()).append("'");
            firstData = false;
        }

        if(whereCondition != null) {
            builder.append(" WHERE ").append(whereCondition);
        }

        builder.append(limitModule.getQueryPart());

        database.runQuery(new PreparedQuery(builder.toString(), now, callback, QueryExecutor.UPDATE));
    }

    @Override public UpdateQuery limit(int limit) {
        limitModule.limit(limit);
        return this;
    }

    @Override public UpdateQuery offset(int offset) {
        limitModule.offset(offset);
        return this;
    }

    @Override public UpdateQuery where(ConditionBuilder condition) {
        whereCondition = condition.toString();
        return this;
    }
}
