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

    @Override
    public Negation cloneWithoutConjunctions() {
        Negation x = new Negation(getLhsOperand().cloneWithoutConjunctions());
        Negation y = new Negation(getRhsOperand().cloneWithoutConjunctions());
        return new Negation(new Disjunction(x, y));
    }

    public Conjunction cloneRemovingDoubleNegation() {
        return new Conjunction(getLhsOperand().cloneRemovingDoubleNegation(),getRhsOperand().cloneRemovingDoubleNegation());
    }
}
