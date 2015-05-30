# KamcioSQL
SQL library for java

Simple usage:
```
Database database = ...; //TODO mysql/sqlite
Queries.select().table("my_table").culumn("a").callback(new QueryCallback() {

    @Override public void done(QueryResult result) throws Exception {
        while(result.next()) {
            System.out.println("Name: " + result.getString("name"));
            System.out.println("Points: " + result.getInt("points"));
        }
    }

    @Override public void error(Throwable t) {
        t.printStackTrace();
    }

}).run(database);
```
