package pl.kamcio96.kamciosql.query;

public interface QueryCallback {

    public void done(QueryResult result) throws Exception ;

    public void error(Throwable t);

}
