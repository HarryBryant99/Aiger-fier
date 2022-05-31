package prop_logic;

import java.util.Objects;

public class Negation extends Expression {

    private final Expression operand;

    public Negation(Expression operand) {
        if (operand == null){
            throw new IllegalArgumentException("Operand cannot be null");
        }
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return "~(" + operand + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Negation negation = (Negation) o;
        return operand.equals(negation.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand);
    }
}
