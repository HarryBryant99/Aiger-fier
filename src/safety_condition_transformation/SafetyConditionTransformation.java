package safety_condition_transformation;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.SafefyConjunction;
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

//            if (neg.getOperand().getClass() == Conjunction.class) {
//                SafetyConditionTransformation.Result splitResult = splitExpression(neg.getOperand());
//                Proposition conjunctionName = ((SafefyConjunction) splitResult.finalExpression).getId();
//                ArrayList<Expression> resultExpressions = new ArrayList<>();
//                resultExpressions.add(splitResult.finalExpression);
//                resultExpressions.addAll(splitResult.expressions);
//                return new SafetyConditionTransformation.Result(resultExpressions, conjunctionName);

//                SafetyConditionTransformation.Result splitResult = splitExpression(neg.getOperand());
//                return splitResult;

//            } else {
                SafetyConditionTransformation.Result splitResult = splitExpression(neg.getOperand());
                return splitResult;
//            }
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            return buildConjunction(exp);
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

    private Expression returnProposition(Expression expression, Result result){
        if (expression.getClass() == Negation.class){
            return new Negation(returnPropositionWhenConjunction(result.finalExpression));
        } else {
            return returnPropositionWhenConjunction(result.finalExpression);
        }
    }

    private Expression returnPropositionWhenConjunction(Expression expression){
        if (expression.getClass() == Negation.class){
            return new Negation(((SafefyConjunction) ((Negation) expression).getOperand()).getId());
        } else {
            return ((SafefyConjunction) expression).getId();
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

    private SafetyConditionTransformation.Result buildConjunction(Expression exp){
        Conjunction con = (Conjunction) exp;
        ArrayList<Expression> resultExpressions = new ArrayList<>();

        if (isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
            Result splitResultLhs = splitExpression(con.getLhsOperand());
            Result splitResultRhs = splitExpression(con.getRhsOperand());
            SafefyConjunction newConjunction = new SafefyConjunction(returnProposition(con.getLhsOperand(),splitResultLhs), returnProposition(con.getRhsOperand(),splitResultLhs), new Proposition(genNewName()));

            resultExpressions.addAll(splitResultLhs.expressions);
            resultExpressions.add(splitResultLhs.finalExpression);
            resultExpressions.addAll(splitResultRhs.expressions);
            resultExpressions.add(splitResultRhs.finalExpression);

            return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

        } else if (isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
            Result splitResultLhs = splitExpression(con.getLhsOperand());
            SafefyConjunction newConjunction = new SafefyConjunction(returnProposition(con.getLhsOperand(),splitResultLhs), con.getRhsOperand(), new Proposition(genNewName()));

            resultExpressions.addAll(splitResultLhs.expressions);
            resultExpressions.add(splitResultLhs.finalExpression);

            return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

        } else if (!isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
            Result splitResultRhs = splitExpression(con.getRhsOperand());
            SafefyConjunction newConjunction = new SafefyConjunction(con.getLhsOperand(), returnProposition(con.getRhsOperand(),splitResultRhs), new Proposition(genNewName()));

            resultExpressions.addAll(splitResultRhs.expressions);
            resultExpressions.add(splitResultRhs.finalExpression);

            return new SafetyConditionTransformation.Result(resultExpressions, newConjunction);

        } else if (!isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
            SafefyConjunction newConjunction = new SafefyConjunction(con.getLhsOperand(), con.getRhsOperand(), new Proposition(genNewName()));
            return new SafetyConditionTransformation.Result(newConjunction);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }
}
