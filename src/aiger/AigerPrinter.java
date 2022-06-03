package aiger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class AigerPrinter {

    private HashMap<String, Integer> propositionKey = new HashMap<String, Integer>();
    private static Integer currentIndex;

    public AigerPrinter() {
        currentIndex = 0;
    }

    public Aig convertLadder(Ladder sourceL) {
        Aig targetAig = new Aig();
        for (Rung r : sourceL.getRungs()) {
            targetAig.addComponent(splitEquivalence(r.getEquivalence()));
        }
        return targetAig;
    }

    private AigerComponent splitEquivalence(Equivalence equiv){
        Integer lhsIndex = propositionReplacer((Proposition) equiv.getLhsOperand(),false);

        String splitResult = splitExpression(equiv.getRhsOperand());

        Expression exp = equiv.getRhsOperand();
        if (exp.getClass() == Proposition.class) {
            return (new Latch(lhsIndex,propositionReplacer(exp, false),0));
        } else if (exp.getClass() == Negation.class) {
            return (new Latch(lhsIndex,propositionReplacer(((Negation) exp).getOperand(), true),0));
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;

            Proposition splitResultLhs = (Proposition) con.getLhsOperand();
            Integer newNameLhs = (genNewName(splitResultLhs.getName()));

            Proposition splitResultRhs = (Proposition) con.getRhsOperand();
            Integer newNameRhs = (genNewName(splitResultRhs.getName()));

            return new And(lhsIndex, newNameLhs, newNameRhs);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private String splitExpression(Expression exp){
//        if (exp.getClass() == Proposition.class) {
//            return propositionReplacer(exp, false);
//        } else if (exp.getClass() == Negation.class) {
//            return propositionReplacer(((Negation) exp).getOperand(), true);
//        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
//            throw new IllegalStateException("How the hell did we get this");
//        } else if (exp.getClass() == Conjunction.class) {
//            Conjunction con = (Conjunction) exp;
//
//            Proposition splitResultLhs = (Proposition) con.getLhsOperand();
//            String newNameLhs = String.valueOf(genNewName(splitResultLhs.getName()));
//
//            Proposition splitResultRhs = (Proposition) con.getRhsOperand();
//            String newNameRhs = String.valueOf(genNewName(splitResultRhs.getName()));
//
//            return (newNameLhs + " " + newNameRhs);
//        } else {
//            throw new IllegalStateException("What is this sub type?");
//        }
        return null;
    }

    private Integer genNewName(String proposition){
        if (isExistingProposition(proposition)){
            return getProposition(proposition);
        }
        newIndex();
        addProposition(proposition);
        return currentIndex;
    }

    private void addProposition(String proposition){
        propositionKey.put(proposition, currentIndex);
    }

    private Integer getProposition(String proposition){
        return propositionKey.get(proposition);
    }

    private void newIndex(){
        currentIndex += 2;
    }

    private boolean isExistingProposition(String proposition){
        return propositionKey.containsKey(proposition);
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

    public Integer propositionReplacer(Expression exp, Boolean isNegative){
        Proposition prop = (Proposition) exp;
        if (isNegative) {
            Integer index = (genNewName(prop.getName())+1);
            return index;
        } else {
            Integer index = (genNewName(prop.getName()));
            return index;
        }
    }
}