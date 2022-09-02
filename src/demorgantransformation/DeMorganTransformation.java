package demorgantransformation;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.DeMorganConjunction;
import tptp.Ladder;
import tptp.Rung;
import tptp.Transition;
import tptp.TransitionRelation;

public class DeMorganTransformation {
    private static final String GEN_NAME_PREFIX = "gen_";
    private int newVarNextNumber = 0;

    private String genNewName() {
        return GEN_NAME_PREFIX + newVarNextNumber++;
    }

    public TransitionRelation transform(Ladder sourceL){
        TransitionRelation targetL = new TransitionRelation();
        for (Rung r : sourceL.getRungs()) {
            //for(Equivalence equ : splitEquivalence(r.getEquivalence().cloneWithoutDisjunctions().cloneRemovingDoubleNegation())) {
                targetL.addTransition(splitEquivalence(r.getEquivalence().cloneWithoutDisjunctions().cloneRemovingDoubleNegation()));
            //}
        }
        return targetL;
    }

    private Transition splitEquivalence(Equivalence equiv){
        DeMorganTransformation.Result splitResult = splitExpression(equiv.getRhsOperand());

        ArrayList<Equivalence> result = new ArrayList<>();
        //result.addAll(splitResult.equivalences);
        //result.add(new Equivalence(equiv.getLhsOperand(), splitResult.finalExpression));

        Transition newT = new Transition(new Equivalence(equiv.getLhsOperand(), splitResult.finalExpression));
        newT.addAllConjunctions(splitResult.equivalences);

        return newT;

        //return result;
    }

    private DeMorganTransformation.Result splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return new DeMorganTransformation.Result(exp);
        } else if (exp.getClass() == Negation.class) {
            Negation neg = (Negation) exp;

            if (((Negation) exp).getOperand().getClass() != Conjunction.class) {
                return new DeMorganTransformation.Result(exp);
            }else {
                DeMorganTransformation.Result splitResult = splitExpression(neg.getOperand());

                ArrayList<DeMorganConjunction> resultEquivs = new ArrayList<>();
                resultEquivs.addAll(splitResult.equivalences);
                //String newName = genNewName();
                //Equivalence equiv = new Equivalence(new Proposition(newName), splitResult.finalExpression);
                //resultEquivs.add(equiv);

                return new DeMorganTransformation.Result(resultEquivs, new Negation(splitResult.finalExpression));
            }
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
//            return buildConjunction(exp);

            Conjunction con = (Conjunction) exp;

            DeMorganTransformation.Result splitResultLhs = splitExpression(con.getLhsOperand());
            //String newNameLhs = genNewName();
            //Equivalence equivLhs = new Equivalence(new Proposition(newNameLhs), splitResultLhs.finalExpression);

            DeMorganTransformation.Result splitResultRhs = splitExpression(con.getRhsOperand());
            //String newNameRhs = genNewName();
            //Equivalence equivRhs = new Equivalence(new Proposition(newNameRhs), splitResultRhs.finalExpression);

            ArrayList<DeMorganConjunction> resultEquivs = new ArrayList<>();
            resultEquivs.addAll(splitResultLhs.equivalences);
            //resultEquivs.add(equivLhs);
            resultEquivs.addAll(splitResultRhs.equivalences);
            //resultEquivs.add(equivRhs);

            if ((isChildConjunction(con.getLhsOperand())) && (isChildConjunction(con.getRhsOperand()))) {
                String newNameLhs = genNewName();
                DeMorganConjunction equivLhs = new DeMorganConjunction(new Proposition(newNameLhs), splitResultLhs.finalExpression, new Proposition("badger"));
                String newNameRhs = genNewName();
                DeMorganConjunction equivRhs = new DeMorganConjunction(new Proposition(newNameRhs), splitResultRhs.finalExpression, new Proposition("badger"));
                resultEquivs.add(equivLhs);
                resultEquivs.add(equivRhs);

                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(equivLhs.getLhsOperand(),
                                equivRhs.getLhsOperand()));
            } else if ((isChildConjunction(con.getLhsOperand())) && (!isChildConjunction(con.getRhsOperand()))) {
                String newNameLhs = genNewName();
                DeMorganConjunction equivLhs = new DeMorganConjunction(new Proposition(newNameLhs), splitResultLhs.finalExpression, new Proposition("badger"));
                resultEquivs.add(equivLhs);

                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(equivLhs.getLhsOperand(),
                                splitResultRhs.finalExpression));
            } else if ((!isChildConjunction(con.getLhsOperand())) && (isChildConjunction(con.getRhsOperand()))) {
                String newNameRhs = genNewName();
                DeMorganConjunction equivRhs = new DeMorganConjunction(splitResultRhs.finalExpression, new Proposition(newNameRhs), new Proposition("badger"));
                resultEquivs.add(equivRhs);

                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(splitResultLhs.finalExpression,
                                equivRhs.getLhsOperand()));
            } else {
                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(splitResultLhs.finalExpression,
                                splitResultRhs.finalExpression));
            }
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private class Result {
        public List<DeMorganConjunction> equivalences;
        public Expression finalExpression;

        public Result(List<DeMorganConjunction> equivalences, Expression finalExpression) {
            this.equivalences = equivalences;
            this.finalExpression = finalExpression;
        }

        public Result(Expression finalExpression) {
            this.equivalences = new ArrayList<>();
            this.finalExpression = finalExpression;
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

    private Expression returnProposition(Expression expression, DeMorganTransformation.Result result){
        if (expression.getClass() == Negation.class){
            return new Negation(returnPropositionWhenConjunction(result.finalExpression));
        } else {
            return returnPropositionWhenConjunction(result.finalExpression);
        }
    }

    private Expression returnPropositionWhenConjunction(Expression expression){
        if (expression.getClass() == Negation.class){
            return new Negation(((DeMorganConjunction) ((Negation) expression).getOperand()).getId());
        } else {
            return ((DeMorganConjunction) expression).getId();
        }
    }
}
