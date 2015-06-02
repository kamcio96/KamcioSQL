package pl.kamcio96.kamciosql.condition;

public class ConditionBuilder {

    private String con;

    private ConditionBuilder(Condition condition) {
        this.con = condition.toString();
    }

    public static ConditionBuilder builder(Condition condition) {
        return new ConditionBuilder(condition);
    }

    public ConditionBuilder or(Condition condition) {
        con = con + " OR " + condition.toString();
        return this;
    }

    public ConditionBuilder or(ConditionBuilder condition) {
        con = "(" + con + ") OR (" + condition.toString() + ")";
        return this;
    }

    public ConditionBuilder and(Condition condition) {
        con = con + " AND " + condition.toString();
        return this;
    }

    public ConditionBuilder and(ConditionBuilder condition) {
        con = "(" + con + ") AND (" + condition.toString() + ")";
        return this;
    }

    public String toString() {
        return con;
    }

    public static Condition like(String column, String value) {
        return new Condition(Condition.ConditionType.LIKE, column, value);
    }

    public static Condition grater(String column, String value) {
        return new Condition(Condition.ConditionType.GREATER_THAN, column, value);
    }

    public static Condition less(String column, String value) {
        return new Condition(Condition.ConditionType.LESS_THAN, column, value);
    }

    public static Condition equal(String column, String value) {
        return new Condition(Condition.ConditionType.EQUALS, column, value);
    }

    public static Condition Null(String column) {
        return new Condition(Condition.ConditionType.NULL, column, null);
    }

    public static class Pre {
        private String name;

        private Pre(String name) {
            this.name = name;
        }

        public Condition like(String value) {
            return new Condition(Condition.ConditionType.LIKE, name, value);
        }

        public Condition like(int value) {
            return new Condition(Condition.ConditionType.LIKE, name, String.valueOf(value));
        }

        public Condition less(int value) {
            return new Condition(Condition.ConditionType.LESS_THAN, name, String.valueOf(value));
        }

        public Condition grater(int value) {
            return new Condition(Condition.ConditionType.GREATER_THAN, name, String.valueOf(value));
        }

        public Condition equal(int value) {
            return new Condition(Condition.ConditionType.EQUALS, name, String.valueOf(value));
        }

        public Condition equal(String value) {
            return new Condition(Condition.ConditionType.EQUALS, name, value);
        }

        public Condition Null() {
            return new Condition(Condition.ConditionType.NULL, name, null);
        }

    }

    public static Pre of(String name) {
        return new Pre(name);
    }

    /*
        builder(like("name", "kamcio96)).or(
            builder( grater("points", "10") ).and( grater("playedTime", "3600") )
        )

        new idea:
        of("name").like("kamcio96");
        of("playedTime").grater(3600);
    */
}
