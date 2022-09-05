package aiger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prop_logic.Conjunction;
import prop_logic.DeMorganConjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;
import tptp.Transition;
import tptp.TransitionRelation;

public class AigerTransitionRelationTransformation {

    private HashMap<String, Integer> propositionKey = new HashMap<String, Integer>();
    private HashMap<String, Boolean> propositionComputed = new HashMap<String, Boolean>();
    private HashMap<String, Integer> initalVariableValues;
    final HashMap<String, Integer> originalInitialVariableValues = new HashMap<>();
    private static Integer currentIndex;

    public AigerTransitionRelationTransformation(HashMap<String, Integer> initalVariableValues) {
        currentIndex = 0;
        this.initalVariableValues = initalVariableValues;
        if (initalVariableValues != null) {
            originalInitialVariableValues.putAll(initalVariableValues);
        }
    }

    public Aig convertRelation(TransitionRelation sourceT) {
        //System.out.println(initalVariableValues);

        Aig targetAig = new Aig();
        for (Transition t : sourceT.getTransitions()) {
            AigerComponent newAig = new Latch(getIntegerForProposition(t.getEquiv().getLhsOperand()),
                    getIntegerForProposition(t.getEquiv().getRhsOperand()),
                    findInitialValue(((Proposition) t.getEquiv().getLhsOperand()).getName(), false));
            targetAig.addComponent(newAig);

            propositionComputed.put(((Proposition) t.getEquiv().getLhsOperand()).getName(), true);

            if (t.getConjunctions().size() != 0) {
                targetAig.addAllComponents(convertConjunctions(t.getConjunctions()));
            }
        }
        //System.out.println("orginal: "+originalInitialVariableValues);

        targetAig.addAllComponents(addInputLatches());

        System.out.println(propositionKey);

        //System.out.println("\n" + initalVariableValues + "\n");

        return targetAig;
    }

    public Aig convertSafetyCondition(SafetyCondition sourceSC) {
        Aig targetAig = new Aig();
        for (Expression exp : sourceSC.getExpression()) {
            targetAig.addComponent(splitExpression(exp));
        }

//        System.out.println(propositionKey);
//        System.out.println(propositionComputed);

        for (Map.Entry<String, Integer> entry : propositionKey.entrySet()) {

            // if give value is equal to value from entry
            // print the corresponding key
        }

        return targetAig;
    }

    private List<AigerComponent> convertConjunctions(ArrayList<DeMorganConjunction> conjunctions) {
        ArrayList<AigerComponent> components = new ArrayList<>();
        for (DeMorganConjunction d:conjunctions) {
            And newAnd = new And(getIntegerForProposition(d.getId()),
                    getIntegerForProposition(d.getLhsOperand()),
                    getIntegerForProposition(d.getRhsOperand()));
            components.add(newAnd);

            propositionComputed.put(d.getId().getName(), true);
        }
        return components;
    }

    private AigerComponent splitExpression(Expression exp) {
        if (exp.getClass() == Proposition.class) {
            return (new Output(propositionReplacer(exp, false)));
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Conjunction.class) {
                return null;
            } else {

                return (new Output(propositionReplacer(((Negation) exp).getOperand(), true)));
            }
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Disjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class) {
            throw new IllegalStateException("Regular Conjunction found in Transitions");
        } else if (exp.getClass() == DeMorganConjunction.class) {
            DeMorganConjunction con = (DeMorganConjunction) exp;

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

    private Integer getIntegerForProposition(Expression exp) {
        if (exp.getClass() == Proposition.class) {
            return (genNewName(((Proposition) exp).getName()));
        } else if (exp.getClass() == Negation.class) {
            return (genNewName(((Proposition) ((Negation) exp).getOperand()).getName()) + 1);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private Integer findInitialValue(String proposition, boolean isNegative) {
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

    private Integer genNewName(String proposition) {
        if (isExistingProposition(proposition)) {
            return getProposition(proposition);
        }
        newIndex();
        addProposition(proposition);
        return currentIndex;
    }

    private void addProposition(String proposition) {
        propositionKey.put(proposition, currentIndex);
        propositionComputed.put(proposition, false);
    }

    private Integer getProposition(String proposition) {
        return propositionKey.get(proposition);
    }

    private void newIndex() {
        currentIndex += 2;
    }

    private boolean isExistingProposition(String proposition) {
        return propositionKey.containsKey(proposition);
    }

    private class Result {
        public List<Equivalence> equivalences;

        public Result(List<Equivalence> equivalences, Expression finalExpression) {
            this.equivalences = equivalences;
        }
    }

    public Integer propositionReplacer(Expression exp, Boolean isNegative) {
        Proposition prop = (Proposition) exp;
        if (isNegative) {
            Integer index = (genNewName(prop.getName()) + 1);
            return index;
        } else {
            Integer index = (genNewName(prop.getName()));
            return index;
        }
    }

    private void updateProposition(Expression exp) {
        if (exp.getClass() == Proposition.class) {
            propositionComputed.replace(((Proposition) exp).getName(), true);
        } else if (exp.getClass() == DeMorganConjunction.class) {
            propositionComputed.replace(((DeMorganConjunction) exp).getId().getName(), true);
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private List<AigerComponent> addInputLatches() {
        ArrayList<AigerComponent> inputLatches = new ArrayList<>();
        for (String prop : propositionKey.keySet()) {
            if (!propositionComputed.get(prop)) {
                Latch newInputLatch = new Latch(getProposition(prop), getProposition(prop),
                        findInitialValue(prop, false));
                inputLatches.add(newInputLatch);
            }
        }
        return inputLatches;
    }

    public int getNumberOfVariables() {
        return propositionKey.size();
    }

    private int computeAnd(Expression lhs, Expression rhs) {
        if ((initalVariableValues.get(((Proposition) lhs).getName()) == 1) &&
                (initalVariableValues.get(((Proposition) rhs).getName()) == 1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
