package prop_logic;

public class Equivalence extends BinaryOperation {

    public Equivalence(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " <=> " + getRhsOperand() + ")";
    }

    @Override
    public Equivalence cloneWithoutDisjunctions() {
        return new Equivalence(getLhsOperand().cloneWithoutDisjunctions(),getRhsOperand().cloneWithoutDisjunctions());
    }

    @Override
    public Equivalence cloneRemovingDoubleNegation() {
        return new Equivalence(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation());
    }
}
