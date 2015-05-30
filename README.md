# KamcioSQL
SQL library for java

Simple usage:
```
Database database = ...; //TODO mysql/sqlite
Queries.select().table("my_table").column("a").callback(new QueryCallback() {

    @Override public void done(QueryResult result) throws Exception {
        while(result.next()) {
            System.out.println("Name: " + result.getString("name"));
            System.out.println("Points: " + result.getInt("points"));
        }
    }

    @Override public void error(Throwable t) {
        t.printStackTrace();
    }

})
//static import
.where(
    builder( like("name", "kamcio96") ).or(
        builder( grater("points", "10") ).and( grater("playedTime", "3600") )
    ).and(builder( equal("type", "player")).or(Null("items").reverse()) )
)
.limit(10).run(database);
```
