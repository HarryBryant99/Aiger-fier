package safety_condition_transformation;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;
import tseitin_transformation.TseitinTransformation;

public class SafetyConditionTransformation {
    private static final String GEN_NAME_PREFIX = "gen_";
    private int newVarNextNumber = 0;

    private String genNewName() {
        return GEN_NAME_PREFIX + newVarNextNumber++;
    }

    public List<Expression> transform(SafetyCondition sourceSC){
        SafetyConditionTransformation.Result splitResult = splitExpression(sourceSC.getExpression().cloneWithoutDisjunctions().cloneRemovingDoubleNegation());

        ArrayList<Expression> result = new ArrayList<>();
        result.add(splitResult.finalExpression);
        result.addAll(splitResult.expressions);

        return result;
    }

    private SafetyConditionTransformation.Result splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class || exp.getClass() == Negation.class) {
            return new SafetyConditionTransformation.Result(exp);
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;

            String newNameLhs = genNewName();
            String newNameRhs = genNewName();
            ArrayList<Expression> resultExpressions = new ArrayList<>();

            if (isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(new Proposition(newNameLhs), new Proposition(newNameRhs));
                Result splitResultLhs = splitExpression(con.getLhsOperand());
                Result splitResultRhs = splitExpression(con.getRhsOperand());

                resultExpressions.addAll(splitResultLhs.expressions);
                resultExpressions.addAll(splitResultRhs.expressions);
                resultExpressions.add(newConjunction);
                return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

            } else if (isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(new Proposition(newNameLhs), con.getRhsOperand());
                Result splitResultLhs = splitExpression(con.getLhsOperand());

                resultExpressions.addAll(splitResultLhs.expressions);
                resultExpressions.add(newConjunction);
                return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

            } else if (!isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(con.getRhsOperand(), new Proposition(newNameLhs));
                Result splitResultRhs = splitExpression(con.getRhsOperand());

                resultExpressions.addAll(splitResultRhs.expressions);
                resultExpressions.add(newConjunction);
                return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

            } else if (!isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
                return new SafetyConditionTransformation.Result(exp);
            } else {
                throw new IllegalStateException("What is this sub type?");
            }
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private boolean isChildConjunction(Expression exp){
        if (exp.getClass() == Conjunction.class){
            return true;
        } else {
            return false;
        }
    }

    private class Result {
        public List<Expression> expressions;
        public Expression finalExpression;

        public Result(List<Expression> expressions, Expression finalExpression) {
            this.expressions = expressions;
            this.finalExpression = finalExpression;
        }

        public Result(Expression finalExpression) {
            this.expressions = new ArrayList<>();
            this.finalExpression = finalExpression;
        }
    }
}
