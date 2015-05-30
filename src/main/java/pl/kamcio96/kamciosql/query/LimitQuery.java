package pl.kamcio96.kamciosql.query;

public interface LimitQuery<T extends LimitQuery> extends Query<T> {

    public T limit(int limit);

    public T offset(int offset);

}
