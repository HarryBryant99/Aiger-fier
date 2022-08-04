package Dimacs_Transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.binding.ListExpression;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class CnfConverter {
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

        Expression splitResult = splitExpression(equiv.getRhsOperand());

        newExpressions.addAll(generateExpressions(equiv.getLhsOperand(),
                    splitResult));

        return newExpressions;
    }

    private Expression splitExpression(Expression exp){
        //ArrayList<Expression> expressions = new ArrayList<>();
        if (exp.getClass() == Proposition.class) {
            return exp;
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Proposition.class) {
                return exp;
            } else if (((Negation) exp).getOperand().getClass() == Conjunction.class) {
                return splitExpression(exp.cloneWithoutConjunctions().cloneRemovingDoubleNegation());
            } else if (((Negation) exp).getOperand().getClass() == Disjunction.class) {
                return splitExpression(exp.cloneWithoutDisjunctions().cloneRemovingDoubleNegation());
            } else {
                throw new IllegalStateException("How the hell did we get this");
            }

        } else if (exp.getClass() == Equivalence.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Disjunction.class) {
            Disjunction dis = (Disjunction) exp;

            if ((dis.getLhsOperand().getClass() == Conjunction.class) &&
                    (dis.getRhsOperand().getClass() == Conjunction.class)){
                //Do 2d loopy thing
                return new Conjunction(new Conjunction(new Conjunction(
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand())),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand()))),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand()))),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand())));
            } else if ((dis.getLhsOperand().getClass() == Conjunction.class) &&
                    (dis.getRhsOperand().getClass() != Conjunction.class)) {
                //Do loopy thing
                return new Conjunction(
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(dis.getRhsOperand())),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(dis.getRhsOperand())));
            } else if ((dis.getLhsOperand().getClass() != Conjunction.class) &&
                    (dis.getRhsOperand().getClass() == Conjunction.class)) {
                //Do loopy thing
                return new Conjunction(
                        new Disjunction(splitExpression(dis.getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand())),
                        new Disjunction(splitExpression(dis.getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand())));
            } else {
                return new Conjunction(splitExpression(dis.getLhsOperand()),splitExpression(dis.getRhsOperand()));
            }

        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;

            if ((con.getLhsOperand().getClass() == Proposition.class) && (con.getRhsOperand().getClass() == Proposition.class)){
                return exp.cloneWithoutConjunctions().cloneRemovingDoubleNegation();
            } else {
                return exp;
            }

        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private List<Expression> returnWhenProposition(Expression p, Expression q){
        List<Expression> newExpressions = new ArrayList<>();
        Expression newExp = new Disjunction(new Negation(p), q);
        Expression newExp2 = new Disjunction(p, new Negation(q));
        newExpressions.add(newExp.cloneRemovingDoubleNegation());
        newExpressions.add(newExp2.cloneRemovingDoubleNegation());

        return newExpressions;
    }

    private List<Expression> returnWhenBothProposition(Expression p, Expression q){
        List<Expression> newExpressions = new ArrayList<>();

        Expression deMorganedQ = q.cloneWithoutDisjunctions().cloneRemovingDoubleNegation();
        Expression newExp = new Disjunction(new Negation(p), ((Conjunction) deMorganedQ).getLhsOperand());
        Expression newExp2 = new Disjunction(new Negation(p), ((Conjunction) deMorganedQ).getRhsOperand());
        Expression newExp3 = new Disjunction(p, new Negation(q));
        newExpressions.add(newExp.cloneRemovingDoubleNegation());
        newExpressions.add(newExp2.cloneRemovingDoubleNegation());
        newExpressions.add(newExp3.cloneRemovingDoubleNegation());

        return newExpressions;
    }

    private List<Expression> generateExpressions(Expression p, Expression q){
        List<Expression> newExpressions = new ArrayList<>();
            if (q.getClass() == Proposition.class){
                newExpressions.addAll(returnWhenProposition(p, q));

            } else if (q.getClass() == Negation.class){
                Negation neg = (Negation) q;
                if (neg.getOperand().getClass() == Proposition.class) {
                    newExpressions.addAll(returnWhenProposition(p, q));
                } else {
                    newExpressions.addAll(returnWhenBothProposition(p, q));
                }

            } else if (q.getClass() == Conjunction.class){
                Conjunction con = (Conjunction) q;
                newExpressions.addAll(returnWhenProposition(p, con.getLhsOperand()));
                newExpressions.addAll(returnWhenProposition(p, con.getRhsOperand()));

            } else if (q.getClass() == Disjunction.class) {
                Disjunction dis = (Disjunction) q;
                newExpressions.addAll(returnWhenProposition(p, dis.getLhsOperand()));
                newExpressions.addAll(returnWhenProposition(p, dis.getRhsOperand()));

            }

        return newExpressions;
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
