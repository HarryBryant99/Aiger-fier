package prop_logic;

public class Disjunction extends BinaryOperation {

    public Disjunction(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " | " + getRhsOperand() + ")";
    }

    @Override
    public Negation cloneWithoutDisjunctions() {
        Negation x = new Negation(getLhsOperand().cloneWithoutDisjunctions());
        Negation y = new Negation(getRhsOperand().cloneWithoutDisjunctions());
        return new Negation(new Conjunction(x, y));
    }

    @Override
    public Disjunction cloneRemovingDoubleNegation() {
        return new Disjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation());
    }
}
