package prop_logic;

import java.util.Arrays;

public abstract class BinaryOperation extends Expression {

    private final Expression[] operands;

    public BinaryOperation(Expression lhs, Expression rhs) {
        if (lhs == null){
            throw new IllegalArgumentException("lhs cannot be null");
        }

        if (rhs == null){
            throw new IllegalArgumentException("rhs cannot be null");
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryOperation that = (BinaryOperation) o;
        return Arrays.equals(operands, that.operands);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(operands);
    }
}
