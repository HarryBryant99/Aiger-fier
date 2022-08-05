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

//        if ((splitResult.getClass() == Negation.class) &&
//                (((Negation) splitResult).getOperand().getClass() != Proposition.class)){
//            newExpressions.addAll(generateExpressions(new Negation(equiv.getLhsOperand()), splitResult));
//        } else {
            newExpressions.addAll(generateExpressions(equiv.getLhsOperand(), splitResult));
//        }

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

            if ((dis.getLhsOperand().getClass() != Proposition.class) &&
                    (dis.getRhsOperand().getClass() != Proposition.class)){

                if ((((Negation) dis.getLhsOperand()).getOperand().getClass() != Proposition.class) && (((Negation) dis.getRhsOperand()).getOperand().getClass() != Proposition.class)) {
                    //Do loopy thing
                    return disjunction(0, exp);
                }
            }

            if ((dis.getLhsOperand().getClass() != Proposition.class) &&
                    (dis.getRhsOperand().getClass() == Proposition.class)) {

                if (((Negation) dis.getLhsOperand()).getOperand().getClass() == Proposition.class) {
                    return exp;
                } else {
                    //Do loopy thing
                    return disjunction(1, exp);
                }
            } else if ((dis.getLhsOperand().getClass() == Proposition.class) &&
                    (dis.getRhsOperand().getClass() != Proposition.class)) {

                if (((Negation) dis.getRhsOperand()).getOperand().getClass() == Proposition.class) {
                    return exp;
                } else {
                    //Do loopy thing
                    return disjunction(2, exp);
                }
            } else {
                return exp;
            }

        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;

            if (((con.getLhsOperand().getClass() == Proposition.class) || (con.getLhsOperand().getClass() == Negation.class))
                    && ((con.getRhsOperand().getClass() == Proposition.class) || (con.getRhsOperand().getClass() == Negation.class))){
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
        Expression deMorganedQ;

        if (q.getClass() == Disjunction.class){
            deMorganedQ = q.cloneWithoutConjunctions().cloneRemovingDoubleNegation();
            Expression newExp3 = new Disjunction(new Negation(p), q);
            Expression newExp = new Disjunction(p, new Negation(((Disjunction) deMorganedQ).getLhsOperand()));
            Expression newExp2 = new Disjunction(p, new Negation(((Disjunction) deMorganedQ).getRhsOperand()));
            newExpressions.add(newExp.cloneRemovingDoubleNegation());
            newExpressions.add(newExp2.cloneRemovingDoubleNegation());
            newExpressions.add(newExp3.cloneRemovingDoubleNegation());

        } else if (q.getClass() == Negation.class) {
            deMorganedQ = q.cloneWithoutDisjunctions().cloneRemovingDoubleNegation();
            Expression newExp = new Disjunction(new Negation(p), ((Conjunction) deMorganedQ).getLhsOperand());
            Expression newExp2 = new Disjunction(new Negation(p), ((Conjunction) deMorganedQ).getRhsOperand());
            Expression newExp3 = new Disjunction(p, new Negation(q));
            newExpressions.add(newExp.cloneRemovingDoubleNegation());
            newExpressions.add(newExp2.cloneRemovingDoubleNegation());
            newExpressions.add(newExp3.cloneRemovingDoubleNegation());

        } else {
            throw new IllegalStateException("Unexpected Expression - Send help");
        }

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
                //newExpressions.addAll(returnWhenProposition(p, con.getLhsOperand()));
                //newExpressions.addAll(returnWhenProposition(p, con.getRhsOperand()));

                newExpressions.addAll(constructExpressions(p, con));

            } else if (q.getClass() == Disjunction.class) {
                Disjunction dis = (Disjunction) q;

                if (((dis.getLhsOperand().getClass() == Proposition.class) || (dis.getLhsOperand().getClass() == Negation.class))
                        && ((dis.getRhsOperand().getClass() == Proposition.class) || (dis.getRhsOperand().getClass() == Negation.class))){
                    newExpressions.addAll(returnWhenBothProposition(p, q));
                } else {
                    newExpressions.addAll(returnWhenProposition(p, dis.getLhsOperand()));
                    newExpressions.addAll(returnWhenProposition(p, dis.getRhsOperand()));
                }
            }

        return newExpressions;
    }

    private List<Expression> constructExpressions(Expression p, Expression q){
        List<Expression> newExpressions = new ArrayList<>();
        Expression newExp = new Disjunction(new Negation(p), q);
        Expression newExp2 = new Disjunction(p, new Negation(q));

        newExpressions.add(splitExpression(newExp));
        //newExpressions.add(splitExpression(newExp2).cloneWithoutConjunctions().cloneRemovingDoubleNegation());


        Expression e = splitExpression(newExp2);
        //newExpressions.add(newExp.cloneWithoutConjunctions().cloneRemovingDoubleNegation());
        newExpressions.add(newExp2.cloneWithoutConjunctions().cloneRemovingDoubleNegation());

        return newExpressions;
    }

    private Expression disjunction (int condition, Expression exp){
        Disjunction dis = ((Disjunction) exp);
        switch (condition){
            case 0:
                return new Conjunction(new Conjunction(new Conjunction(
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand())),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand()))),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand()))),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand())));
            case 1:
                return new Conjunction(
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getLhsOperand()),
                                splitExpression(dis.getRhsOperand())),
                        new Disjunction(splitExpression(((Conjunction) dis.getLhsOperand()).getRhsOperand()),
                                splitExpression(dis.getRhsOperand())));
            case 2:
                return new Conjunction(
                        new Disjunction(splitExpression(dis.getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getLhsOperand())),
                        new Disjunction(splitExpression(dis.getLhsOperand()),
                                splitExpression(((Conjunction) dis.getRhsOperand()).getRhsOperand())));
            case 3:
                return exp;
            default:
                throw new IllegalStateException("Unexpected - Send help");
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
