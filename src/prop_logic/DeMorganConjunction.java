package prop_logic;

import java.util.Objects;

public class DeMorganConjunction extends BinaryOperation {
    private final Proposition id;

    public DeMorganConjunction(Expression lhs, Expression rhs, Proposition id) {
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
    public DeMorganConjunction cloneWithoutDisjunctions() {
        return new DeMorganConjunction(getLhsOperand().cloneWithoutDisjunctions(),getRhsOperand().cloneWithoutDisjunctions(), id);
    }

    @Override
    public DeMorganConjunction cloneWithoutConjunctions() {
        return new DeMorganConjunction(getLhsOperand().cloneWithoutConjunctions(),getRhsOperand().cloneWithoutConjunctions(), id);
    }

    public DeMorganConjunction cloneRemovingDoubleNegation() {
        return new DeMorganConjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation(), id);
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
        DeMorganConjunction that = (DeMorganConjunction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
