package pl.kamcio96.kamciosql.query;

public interface SelectQuery extends Query<SelectQuery>, LimitQuery<SelectQuery>, WhereQuery<SelectQuery> {

    public SelectQuery culumn(String... column);
}
