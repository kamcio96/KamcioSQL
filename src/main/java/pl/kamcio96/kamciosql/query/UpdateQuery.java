package pl.kamcio96.kamciosql.query;

public interface UpdateQuery extends LimitQuery<UpdateQuery>, WhereQuery<UpdateQuery> {

    public void add(String column, String value);

}
