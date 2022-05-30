package prop_logic;

public class Equivalence extends BinaryOperation {

    public Equivalence(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " <=> " + getRhsOperand() + ")";
    }
}
