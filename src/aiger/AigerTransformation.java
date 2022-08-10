package aiger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.SafetyConjunction;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;

public class AigerTransformation {

    private HashMap<String, Integer> propositionKey = new HashMap<String, Integer>();
    private HashMap<String, Boolean> propositionComputed = new HashMap<String, Boolean>();
    private HashMap<String, Integer> initalVariableValues;
    private static Integer currentIndex;

    public AigerTransformation(HashMap<String,Integer> initalVariableValues) {
        currentIndex = 0;
        this.initalVariableValues = initalVariableValues;
    }

    public Aig convertLadder(Ladder sourceL) {
        Aig targetAig = new Aig();
        for (Rung r : sourceL.getRungs()) {
            AigerComponent newAig = splitEquivalence(r.getEquivalence());

            if (newAig.getClass() == Latch.class) {
                initalVariableValues.put(((Proposition) r.getEquivalence().getLhsOperand()).getName(),
                        ((Latch) newAig).getOriginal());
            } else if (newAig.getClass() == And.class) {
                initalVariableValues.put(((Proposition) r.getEquivalence().getLhsOperand()).getName(),
                        computeAnd(((Conjunction) r.getEquivalence().getRhsOperand()).getLhsOperand(),
                                ((Conjunction) r.getEquivalence().getRhsOperand()).getRhsOperand()));
            } else {
                throw new IllegalStateException("Ay??? What is this?");
            }

            targetAig.addComponent(newAig);
            updateProposition(r.getEquivalence().getLhsOperand());
        }

        targetAig.addAllComponents(addInputLatches());

        System.out.println(propositionKey);
        return targetAig;
    }

    public Aig convertSafetyCondition(SafetyCondition sourceSC) {
        Aig targetAig = new Aig();
        for (Expression exp : sourceSC.getExpression()) {
            targetAig.addComponent(splitExpression(exp));
        }

//        System.out.println(propositionKey);
//        System.out.println(propositionComputed);

        for(Map.Entry<String, Integer> entry: propositionKey.entrySet()) {

            // if give value is equal to value from entry
            // print the corresponding key
        }

        return targetAig;
    }

    private AigerComponent splitEquivalence(Equivalence equiv){
        Integer lhsIndex = propositionReplacer((Proposition) equiv.getLhsOperand(),false);

        Expression exp = equiv.getRhsOperand();

        if (exp.getClass() == Proposition.class) {
            String rhsName = ((Proposition) exp).getName();
            return (new Latch(lhsIndex,propositionReplacer(exp, false),findInitialValue(rhsName, false)));
        } else if (exp.getClass() == Negation.class) {
            String rhsName = ((Proposition) ((Negation) exp).getOperand()).getName();
            return (new Latch(lhsIndex,propositionReplacer(((Negation) exp).getOperand(), true),findInitialValue(rhsName, true)));
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            Conjunction con = (Conjunction) exp;

            Proposition splitResultLhs = (Proposition) con.getLhsOperand();
            Integer newNameLhs = (genNewName(splitResultLhs.getName()));

            Proposition splitResultRhs = (Proposition) con.getRhsOperand();
            Integer newNameRhs = (genNewName(splitResultRhs.getName()));

            return new And(lhsIndex, newNameLhs, newNameRhs);
        } else if (exp.getClass() == SafetyConjunction.class) {
            throw new IllegalStateException("SafetyConjunction found in Transitions");
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private AigerComponent splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return (new Output(propositionReplacer(exp, false)));
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Conjunction.class){
                return null;
            } else {

                return (new Output(propositionReplacer(((Negation) exp).getOperand(), true)));
            }
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            throw new IllegalStateException("Regular Conjunction found in Transitions");
        } else if (exp.getClass() == SafetyConjunction.class) {
            SafetyConjunction con = (SafetyConjunction) exp;

            Proposition splitResultId = con.getId();
            Integer newNameId = (genNewName(splitResultId.getName()));

            Expression splitResultLhs = con.getLhsOperand();

            Integer newNameLhs = getIntegerForProposition(splitResultLhs);

            Expression splitResultRhs = con.getRhsOperand();
            Integer newNameRhs = getIntegerForProposition(splitResultRhs);

            return new And(newNameId, newNameLhs, newNameRhs);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private Integer getIntegerForProposition(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return (genNewName(((Proposition) exp).getName()));
        } else if (exp.getClass() == Negation.class){
            return (genNewName(((Proposition) ((Negation) exp).getOperand()).getName())+1);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private Integer findInitialValue(String proposition, boolean isNegative){
        Integer initialValue = 0;
        if (initalVariableValues != null) {
            if (initalVariableValues.containsKey(proposition)) {
                initialValue = initalVariableValues.get(proposition);
                if (isNegative) {
                    if (initialValue == 1) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            } else {
                if (isNegative) {
                    return 1;
                } else {
                    return 0;
                }
                //return initialValue;
            }
        }
        return initialValue;
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
        propositionComputed.put(proposition, false);
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

        public Result(List<Equivalence> equivalences, Expression finalExpression) {
            this.equivalences = equivalences;
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

    private void updateProposition(Expression exp){
        if (exp.getClass() == Proposition.class) {
            propositionComputed.replace(((Proposition) exp).getName(), true);
        } else if (exp.getClass() == SafetyConjunction.class) {
            propositionComputed.replace(((SafetyConjunction) exp).getId().getName(), true);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private List<AigerComponent> addInputLatches(){
        ArrayList<AigerComponent> inputLatches = new ArrayList<>();
        for (String prop : propositionKey.keySet()) {
            if (!propositionComputed.get(prop)){
                Latch newInputLatch = new Latch(getProposition(prop),getProposition(prop),findInitialValue(prop, false));
                inputLatches.add(newInputLatch);
            }
        }
        return inputLatches;
    }

    public int getNumberOfVariables(){
        return propositionKey.size();
    }

    private int computeAnd(Expression lhs, Expression rhs){
        if ((initalVariableValues.get(((Proposition) lhs).getName()) == 1) &&
        (initalVariableValues.get(((Proposition) rhs).getName()) == 1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
