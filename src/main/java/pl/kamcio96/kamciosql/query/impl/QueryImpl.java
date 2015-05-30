package pl.kamcio96.kamciosql.query.impl;

import pl.kamcio96.kamciosql.query.Query;
import pl.kamcio96.kamciosql.query.QueryCallback;

public abstract class QueryImpl<T extends Query> implements Query<T> {

    protected String table = null;
    protected boolean now = false;
    protected QueryCallback callback;

    @Override public T table(String table) {
        this.table = table;
        return (T) this;
    }

    @Override public T now(boolean now) {
        this.now = now;
        return (T) this;
    }

    @Override public T callback(QueryCallback callback) {
        this.callback = callback;
        return (T) this;
    }
}
