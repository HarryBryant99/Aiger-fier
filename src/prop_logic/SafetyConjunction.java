package prop_logic;

import java.util.Objects;

public class SafetyConjunction extends BinaryOperation {
    private final Proposition id;

    public SafetyConjunction(Expression lhs, Expression rhs, Proposition id) {
        super(lhs, rhs);
        this.id = id;
    }

    public Proposition getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": (" + getLhsOperand() + " & " + getRhsOperand() + ")";
    }

    @Override
    public SafetyConjunction cloneWithoutDisjunctions() {
        return new SafetyConjunction(getLhsOperand().cloneWithoutDisjunctions(),getRhsOperand().cloneWithoutDisjunctions(), id);
    }

    @Override
    public SafetyConjunction cloneWithoutConjunctions() {
        return new SafetyConjunction(getLhsOperand().cloneWithoutConjunctions(),getRhsOperand().cloneWithoutConjunctions(), id);
    }

    public SafetyConjunction cloneRemovingDoubleNegation() {
        return new SafetyConjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation(), id);
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
        SafetyConjunction that = (SafetyConjunction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
