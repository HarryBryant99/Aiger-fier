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

    public SafetyCondition transform(SafetyCondition sourceSC){
        SafetyCondition targetSC = new SafetyCondition();
        Expression formattedSC = sourceSC.getExpression().get(0).cloneWithoutDisjunctions().cloneRemovingDoubleNegation();

        SafetyConditionTransformation.Result splitResult = splitExpression(formattedSC);

        if (formattedSC.getClass() == Negation.class){
            targetSC.addExpression(new Negation(splitResult.finalExpression));
        } else {
            targetSC.addExpression(splitResult.finalExpression);
        }
        targetSC.addAllExpressions(splitResult.expressions);

        return targetSC;
    }

    private SafetyConditionTransformation.Result splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return new SafetyConditionTransformation.Result(exp);
        } else if (exp.getClass() == Negation.class){
            Negation neg = (Negation) exp;

            //if (neg.getOperand().getClass() == Conjunction.class){
                SafetyConditionTransformation.Result splitResult = splitExpression(neg.getOperand());

                //return new SafetyConditionTransformation.Result(splitResult.finalExpression);
                return splitResult;
            //} else {
                //return new SafetyConditionTransformation.Result(exp);
            //}
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;
            ArrayList<Expression> resultExpressions = new ArrayList<>();

            if (isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(returnProposition(con.getLhsOperand()), returnProposition(con.getRhsOperand()));
                Result splitResultLhs = splitExpression(con.getLhsOperand());
                Result splitResultRhs = splitExpression(con.getRhsOperand());

                resultExpressions.add(splitResultLhs.finalExpression);
                resultExpressions.addAll(splitResultLhs.expressions);
                resultExpressions.add(splitResultRhs.finalExpression);
                resultExpressions.addAll(splitResultRhs.expressions);
                return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

            } else if (isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(returnProposition(con.getLhsOperand()), con.getRhsOperand());
                Result splitResultLhs = splitExpression(con.getLhsOperand());

                resultExpressions.add(splitResultLhs.finalExpression);
                resultExpressions.addAll(splitResultLhs.expressions);
                return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

            } else if (!isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
                Conjunction newConjunction = new Conjunction(con.getLhsOperand(), returnProposition(con.getRhsOperand()));
                Result splitResultRhs = splitExpression(con.getRhsOperand());

                resultExpressions.add(splitResultRhs.finalExpression);
                resultExpressions.addAll(splitResultRhs.expressions);
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
        if (exp.getClass() == Negation.class){
            exp = ((Negation) exp).getOperand();
        }

        if (exp.getClass() == Conjunction.class){
            return true;
        } else {
            return false;
        }
    }

    private Expression returnProposition(Expression expression){
        if (expression.getClass() == Negation.class){
            return new Negation(new Proposition(genNewName()));
        } else {
            return new Proposition(genNewName());
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
