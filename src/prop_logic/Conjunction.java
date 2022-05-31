package prop_logic;

public class Conjunction extends BinaryOperation {

    public Conjunction(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " & " + getRhsOperand() + ")";
    }

    @Override
    public Conjunction cloneWithoutDisjunctions() {
        return new Conjunction(getLhsOperand().cloneWithoutDisjunctions(),getRhsOperand().cloneWithoutDisjunctions());
    }

    public Conjunction cloneRemovingDoubleNegation() {
        return new Conjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation());
    }
}
