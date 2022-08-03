package Dimacs_Transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class EquivalenceRemover {
    private static final String GEN_NAME_PREFIX = "gen_";
    private int newVarNextNumber = 0;

    private String genNewName() {
        return GEN_NAME_PREFIX + newVarNextNumber++;
    }

    public List<Expression> transform(Ladder sourceL){
        ArrayList<Expression> targetList = new ArrayList<>();
        for (Rung r : sourceL.getRungs()) {
            targetList.addAll(splitEquivalence(r.getEquivalence()));
        }
        return targetList;
    }

    private List<Expression> splitEquivalence(Equivalence equiv){
        List<Expression> newExpressions = new ArrayList<>();

        List<Expression> decomposedResult = decomposeExpression(equiv.getRhsOperand());

        Expression exp = new Disjunction(new Negation(equiv.getLhsOperand()), decomposedResult.get(0));

        for (int i = 1; i < decomposedResult.size(); i++) {
            exp = new Disjunction(exp, decomposedResult.get(i));
        }

        exp = exp.cloneRemovingDoubleNegation();

        newExpressions.add(exp);

        List<Expression> splitResult = splitExpression(equiv.getRhsOperand());

        for (int i = 0; i < splitResult.size(); i++) {
            Expression newExp = new Disjunction(equiv.getLhsOperand(), splitResult.get(i));
            newExpressions.add(newExp.cloneRemovingDoubleNegation());
        }

        return newExpressions;
    }

    private List<Expression> splitExpression(Expression exp){
        ArrayList<Expression> expressions = new ArrayList<>();
        if (exp.getClass() == Proposition.class) {
            Negation neg = new Negation(exp);
            expressions.add(neg);
            return expressions;
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Proposition.class) {
                expressions.add(((Negation) exp).getOperand());
            } else {
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(((Negation) exp).getOperand()));
            }
            return expressions;
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Conjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Disjunction.class) {
            Disjunction dis = (Disjunction) exp;
            if (((Disjunction) exp).getLhsOperand().getClass() == Disjunction.class){
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(dis.getLhsOperand()));
            } else {
                expressions.add(new Negation (((Disjunction) exp).getLhsOperand()));
            }

            if (((Disjunction) exp).getRhsOperand().getClass() == Disjunction.class){
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(dis.getRhsOperand()));
            } else {
                expressions.add(new Negation (((Disjunction) exp).getRhsOperand()));
            }

            return expressions;
        } else {
            throw new IllegalStateException("What is thi sub type?");
        }
    }

    private List<Expression> decomposeExpression(Expression exp){
        ArrayList<Expression> expressions = new ArrayList<>();
        if (exp.getClass() == Proposition.class) {
            expressions.add(exp);
            return expressions;
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Proposition.class) {
                expressions.add(exp);
            } else {
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(((Negation) exp).getOperand()));
            }
            return expressions;
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Conjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Disjunction.class) {
            Disjunction dis = (Disjunction) exp;
            if (((Disjunction) exp).getLhsOperand().getClass() == Disjunction.class){
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(dis.getLhsOperand()));
            } else {
                expressions.add(new Negation (((Disjunction) exp).getLhsOperand()));
            }

            if (((Disjunction) exp).getRhsOperand().getClass() == Disjunction.class){
                expressions.addAll(
                        (Collection<? extends Expression>) splitExpression(dis.getRhsOperand()));
            } else {
                expressions.add(new Negation (((Disjunction) exp).getRhsOperand()));
            }

            return expressions;
        } else {
            throw new IllegalStateException("What is thi sub type?");
        }
    }

    private class Result {
        public List<Equivalence> equivalences;
        public Expression finalExpression;

        public Result(List<Equivalence> equivalences, Expression finalExpression) {
            this.equivalences = equivalences;
            this.finalExpression = finalExpression;
        }

        public Result(Expression finalExpression) {
            this.equivalences = new ArrayList<>();
            this.finalExpression = finalExpression;
        }
    }
}
