package demorgantransformation;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.SafetyConjunction;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;
import tptp.Transition;
import tptp.TransitionRelation;
import tseitin_transformation.TseitinTransformation;

public class DeMorganTransformation {
    private static final String GEN_NAME_PREFIX = "gen_";
    private int newVarNextNumber = 0;

    private String genNewName() {
        return GEN_NAME_PREFIX + newVarNextNumber++;
    }

    public TransitionRelation transform(Ladder sourceL){
        TransitionRelation targetL = new TransitionRelation();
        for (Rung r : sourceL.getRungs()) {
            for(Equivalence equ : splitEquivalence(r.getEquivalence().cloneWithoutDisjunctions().cloneRemovingDoubleNegation())) {
                targetL.addTransition(new Transition(equ));
            }
        }
        return targetL;
    }

    private List<Equivalence> splitEquivalence(Equivalence equiv){
        DeMorganTransformation.Result splitResult = splitExpression(equiv.getRhsOperand());

        ArrayList<Equivalence> result = new ArrayList<>();
        result.addAll(splitResult.equivalences);
        result.add(new Equivalence(equiv.getLhsOperand(), splitResult.finalExpression));



        return result;
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

                ArrayList<Equivalence> resultEquivs = new ArrayList<>();
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

            ArrayList<Equivalence> resultEquivs = new ArrayList<>();
            resultEquivs.addAll(splitResultLhs.equivalences);
            //resultEquivs.add(equivLhs);
            resultEquivs.addAll(splitResultRhs.equivalences);
            //resultEquivs.add(equivRhs);

            if ((isChildConjunction(con.getLhsOperand())) && (isChildConjunction(con.getRhsOperand()))) {
                String newNameLhs = genNewName();
                Equivalence equivLhs = new Equivalence(new Proposition(newNameLhs), splitResultLhs.finalExpression);
                String newNameRhs = genNewName();
                Equivalence equivRhs = new Equivalence(new Proposition(newNameRhs), splitResultRhs.finalExpression);
                resultEquivs.add(equivLhs);
                resultEquivs.add(equivRhs);

                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(equivLhs.getLhsOperand(),
                                equivRhs.getLhsOperand()));
            } else if ((isChildConjunction(con.getLhsOperand())) && (!isChildConjunction(con.getRhsOperand()))) {
                String newNameLhs = genNewName();
                Equivalence equivLhs = new Equivalence(new Proposition(newNameLhs), splitResultLhs.finalExpression);
                resultEquivs.add(equivLhs);

                return new DeMorganTransformation.Result(resultEquivs,
                        new Conjunction(equivLhs.getLhsOperand(),
                                splitResultRhs.finalExpression));
            } else if ((!isChildConjunction(con.getLhsOperand())) && (isChildConjunction(con.getRhsOperand()))) {
                String newNameRhs = genNewName();
                Equivalence equivRhs = new Equivalence(new Proposition(newNameRhs), splitResultRhs.finalExpression);
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

//    private DeMorganTransformation.Result buildConjunction(Expression exp){
//        Conjunction con = (Conjunction) exp;
//        ArrayList<Expression> resultExpressions = new ArrayList<>();
//
//        if (isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
//            DeMorganTransformation.Result splitResultLhs = splitExpression(con.getLhsOperand());
//            DeMorganTransformation.Result splitResultRhs = splitExpression(con.getRhsOperand());
//            SafetyConjunction newConjunction = new SafetyConjunction(returnProposition(con.getLhsOperand(),splitResultLhs), returnProposition(con.getRhsOperand(),splitResultRhs), new Proposition(genNewName()));
//
//            resultExpressions.addAll(splitResultLhs.equivalences);
//            resultExpressions.add(splitResultLhs.finalExpression);
//            resultExpressions.addAll(splitResultRhs.equivalences);
//            resultExpressions.add(splitResultRhs.finalExpression);
//
//            return new DeMorganTransformation.Result(resultExpressions, newConjunction);
//
//        } else if (isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
//            DeMorganTransformation.Result splitResultLhs = splitExpression(con.getLhsOperand());
//            SafetyConjunction newConjunction = new SafetyConjunction(returnProposition(con.getLhsOperand(),splitResultLhs), con.getRhsOperand(), new Proposition(genNewName()));
//
//            resultExpressions.addAll(splitResultLhs.expressions);
//            resultExpressions.add(splitResultLhs.finalExpression);
//
//            return new DeMorganTransformation.Result(resultExpressions, newConjunction);
//
//        } else if (!isChildConjunction(con.getLhsOperand()) && isChildConjunction(con.getRhsOperand())){
//            DeMorganTransformation.Result splitResultRhs = splitExpression(con.getRhsOperand());
//            SafetyConjunction
//                    newConjunction = new SafetyConjunction(con.getLhsOperand(), returnProposition(con.getRhsOperand(),splitResultRhs), new Proposition(genNewName()));
//
//            resultExpressions.addAll(splitResultRhs.expressions);
//            resultExpressions.add(splitResultRhs.finalExpression);
//
//            return new DeMorganTransformation.Result(resultExpressions, newConjunction);
//
//        } else if (!isChildConjunction(con.getLhsOperand()) && !isChildConjunction(con.getRhsOperand())){
//            SafetyConjunction newConjunction = new SafetyConjunction(con.getLhsOperand(), con.getRhsOperand(), new Proposition(genNewName()));
//            return new DeMorganTransformation.Result(newConjunction);
//        } else {
//            throw new IllegalStateException("What is this sub type?");
//        }
//    }

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
            return new Negation(((SafetyConjunction) ((Negation) expression).getOperand()).getId());
        } else {
            return ((SafetyConjunction) expression).getId();
        }
    }
}
