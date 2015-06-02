package pl.kamcio96.kamciosql;

import org.junit.Test;
import pl.kamcio96.kamciosql.query.Query;
import pl.kamcio96.kamciosql.query.QueryCallback;
import pl.kamcio96.kamciosql.query.QueryResult;
import pl.kamcio96.kamciosql.query.SelectQuery;
import pl.kamcio96.kamciosql.query.impl.PreparedQuery;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import static pl.kamcio96.kamciosql.condition.ConditionBuilder.*;

public class QueryTest implements TaskProvider {

    @Test
    public void selectTest() {

        Database db = Database.newMySQL(this, "sql5.freemysqlhosting.net", "sql579037", "sql579037", "hL5%hG1*");
        db.setDebug(true);

        QueryCallback callback = new QueryCallback() {

            @Override public void done(QueryResult result) throws Exception {
                while (result.next()) {
                    System.out.println("Name: " + result.getString("name"));
                    System.out.println("Version: " + result.getInt("version"));
                }
            }

            @Override public void error(Throwable t) {
                t.printStackTrace();
            }
        };

        SelectQuery base = Queries.select().now(true).table("users").callback(callback);

        base.clone().run(db);
        base.clone().limit(1).run(db);
        base.clone().where(of("version").equal(1)).run(db);
        base.clone().where(equal("version", "1")).run(db);
        base.clone().where(builder(equal("version", "1")).or(equal("loginTime", "2"))).run(db);
        base.clone().where(builder( of("version").equal(1) ).or( of("loginTime").equal(2) )).run(db);
        base.clone().column("name", "version").where(like("name", "kamcio96").reverse()).run(db);
    }

    @Override public Logger getLogger() {
        return Logger.getLogger("task");
    }

    @Override public void runTask(Runnable runnable) {
        new Thread(runnable).start();
    }

    @Override public void runTaskTimer(final Runnable runnableTask, long peroid) {
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override public void run() {
                runnableTask.run();
            }
        }, peroid, peroid);
    }

}
