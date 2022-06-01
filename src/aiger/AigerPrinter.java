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

    public Ladder convertLadder(Ladder sourceL) {
        Ladder targetL = new Ladder();
        for (Rung r : sourceL.getRungs()) {
            for(Equivalence equ : splitEquivalence(r.getEquivalence())) {
                targetL.addRung(new Rung(equ));
            }
        }
        return targetL;
    }

    private List<Equivalence> splitEquivalence(Equivalence equiv){
        Proposition lhsIndex = propositionReplacer((Proposition) equiv.getLhsOperand(),false);

        AigerPrinter.Result splitResult = splitExpression(equiv.getRhsOperand());

        ArrayList<Equivalence> result = new ArrayList<>();
        result.addAll(splitResult.equivalences);
        result.add(new Equivalence(lhsIndex, splitResult.finalExpression));

        return result;
    }

    private AigerPrinter.Result splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return new AigerPrinter.Result(propositionReplacer(exp, false));
        } else if (exp.getClass() == Negation.class) {
            return new AigerPrinter.Result(propositionReplacer(((Negation) exp).getOperand(), true));
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;



            AigerPrinter.Result splitResultLhs = splitExpression(con.getLhsOperand());
            String newNameLhs = String.valueOf(genNewName("Test"));
            Equivalence equivLhs = new Equivalence(new Proposition(newNameLhs), splitResultLhs.finalExpression);

            AigerPrinter.Result splitResultRhs = splitExpression(con.getRhsOperand());
            String newNameRhs = String.valueOf(genNewName("Test"));
            Equivalence equivRhs = new Equivalence(new Proposition(newNameRhs), splitResultRhs.finalExpression);

            ArrayList<Equivalence> resultEquivs = new ArrayList<>();
            resultEquivs.addAll(splitResultLhs.equivalences);
            resultEquivs.add(equivLhs);
            resultEquivs.addAll(splitResultRhs.equivalences);
            resultEquivs.add(equivRhs);

            return new AigerPrinter.Result(resultEquivs, new Conjunction(new Proposition(newNameLhs), new Proposition(newNameRhs)));
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
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

    public Proposition propositionReplacer(Expression exp, Boolean isNegative){
        Proposition prop = (Proposition) exp;
        if (isNegative) {
            Proposition index = new Proposition(String.valueOf(genNewName(prop.getName())+1));
            return index;
        } else {
            Proposition index = new Proposition(String.valueOf(genNewName(prop.getName())));
            return index;
        }
    }
}
