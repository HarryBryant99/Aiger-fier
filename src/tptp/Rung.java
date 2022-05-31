package tptp;

import java.util.Objects;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Proposition;

public class Rung {

    private Expression expression;

    public Rung(Expression expression) {
        assert expression != null;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rung rung = (Rung) o;
        return expression.equals(rung.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
