package pl.kamcio96.kamciosql.query.impl;

import pl.kamcio96.kamciosql.QueryExecutor;
import pl.kamcio96.kamciosql.query.QueryCallback;

public final class PreparedQuery {

    private String query;
    private boolean now;
    private QueryCallback callback;
    private QueryExecutor executor;

    PreparedQuery(String query, boolean now, QueryCallback callback, QueryExecutor executor) {
        this.query = query;
        this.now = now;
        this.callback = callback;
        this.executor = executor;
    }

    public String getQuery() {
        return this.query;
    }

    public boolean isNow() {
        return this.now;
    }

    public QueryCallback getCallback() {
        return this.callback;
    }

    public QueryExecutor getExecutor() {
        return this.executor;
    }
}
