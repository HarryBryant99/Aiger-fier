package prop_logic;

public class Disjunction extends BinaryOperation {

    public Disjunction(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " | " + getRhsOperand() + ")";
    }
}
