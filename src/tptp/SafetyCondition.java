package tptp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import prop_logic.Expression;

public class SafetyCondition {

    private Expression condition;

    public SafetyCondition() {
    }

    public void addExpression(Expression newExpression){
        Objects.nonNull(newExpression);
        condition = newExpression;
    }

    public Expression getExpression(){
        return condition;
    }


    @Override
    public String toString() {
        return "SafetyCondition{" +
                "condition=" + condition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SafetyCondition that = (SafetyCondition) o;
        return condition.equals(that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition);
    }
}
