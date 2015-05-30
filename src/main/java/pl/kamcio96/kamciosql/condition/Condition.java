package pl.kamcio96.kamciosql.condition;

import com.google.common.base.Preconditions;

public class Condition {

    private ConditionType type;
    private String column;
    private String value;
    private boolean reverse = false;

    protected Condition(ConditionType type, String column, String value) {
        Preconditions.checkNotNull(type, "type");
        Preconditions.checkNotNull(column, "column");
        if( type != ConditionType.NULL ) {
            Preconditions.checkNotNull(value, "value");
        }
        this.type = type;
        this.column = column;
        this.value = value;
    }

    public Condition reverse() {
        reverse = !reverse;
        return this;
    }

    public String toString() {
        return "`" + column + "`" + (reverse ? type.conditionReverseString : type.conditionString) + type.parseValue(value);
    }

    protected enum ConditionType {

        EQUALS("=", "!="),
        GREATER_THAN(">", ">="),
        LESS_THAN("<", ">="),
        LIKE(" LIKE ", " NOT LIKE "),
        NULL("=", "!=");

        private String conditionString;
        private String conditionReverseString;

        ConditionType(String conditionString, String conditionReverseString) {
            this.conditionString = conditionString;
            this.conditionReverseString = conditionReverseString;
        }

        String parseValue(String value) {
            if(this == NULL) {
                return "NULL";
            }
            return "'" + value + "'";
        }
    }
}
