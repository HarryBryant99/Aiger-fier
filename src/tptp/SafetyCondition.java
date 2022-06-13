package tptp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import prop_logic.Expression;

public class SafetyCondition {

    private ArrayList<Expression> conditions = new ArrayList<>();

    public SafetyCondition() {
    }

    public void addExpression(Expression newExpression){
        Objects.nonNull(newExpression);
        conditions.add(newExpression);
    }

    public void addAllExpressions(List<Expression> newExpressions){
        Objects.nonNull(newExpressions);
        conditions.addAll(newExpressions);
    }

    public void updateExpression(int i, Expression expression){
        conditions.set(i,expression);
    }

    public ArrayList<Expression> getExpression(){
        return conditions;
    }


    @Override
    public String toString() {
        return "SafetyCondition{" +
                "conditions=" + conditions +
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
        return conditions.equals(that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }
}
