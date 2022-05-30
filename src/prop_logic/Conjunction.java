package prop_logic;

public class Conjunction extends BinaryOperation {

    public Conjunction(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + getLhsOperand() + " & " + getRhsOperand() + ")";
    }
}
