package prop_logic;

import java.util.Objects;

public class SafefyConjunction extends BinaryOperation {
    private final String id;

    public SafefyConjunction(Expression lhs, Expression rhs, String id) {
        super(lhs, rhs);
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ": (" + getLhsOperand() + " & " + getRhsOperand() + ")";
    }

    @Override
    public SafefyConjunction cloneWithoutDisjunctions() {
        return new SafefyConjunction(getLhsOperand().cloneWithoutDisjunctions(),getRhsOperand().cloneWithoutDisjunctions(), id);
    }

    public SafefyConjunction cloneRemovingDoubleNegation() {
        return new SafefyConjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SafefyConjunction that = (SafefyConjunction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
