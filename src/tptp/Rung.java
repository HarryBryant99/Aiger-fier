package tptp;

import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Proposition;

public class Rung {

    private Expression expression;

    public Rung(Expression expression) {
        if (expression.getClass() != Equivalence.class){
            throw new IllegalArgumentException("Root expression must be an equivalence");
        }

        Expression lhs = ((Equivalence) expression).getLhsOperand();
        if (lhs.getClass() != Proposition.class){
            throw new IllegalArgumentException("Root equivalence must have a proposition on lhs");
        }

        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Rung{" + expression + "}";
    }
}
