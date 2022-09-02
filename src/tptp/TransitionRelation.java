package tptp;

import java.util.ArrayList;
import prop_logic.Conjunction;
import prop_logic.Expression;

public class TransitionRelation {
    private ArrayList<Transition> transitions = new ArrayList<>();

    public TransitionRelation() {
        this.transitions = transitions;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }
}
