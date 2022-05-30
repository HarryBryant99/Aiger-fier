package prop_logic;

public class Negation extends Expression {

    private final Expression operand;

    public Negation(Expression operand) {
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return "~(" + operand + ")";
    }
}
