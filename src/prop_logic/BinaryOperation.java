package prop_logic;

public abstract class BinaryOperation extends Expression {

    private final Expression[] operands;

    public BinaryOperation(Expression lhs, Expression rhs) {
        operands = new Expression[2];
        operands[0] = lhs;
        operands[1] = rhs;
    }

    public Expression getOperand(int i) {
        return operands[i];
    }

    public Expression getLhsOperand() {
        return getOperand(0);
    }

    public Expression getRhsOperand() {
        return getOperand(1);
    }
}
