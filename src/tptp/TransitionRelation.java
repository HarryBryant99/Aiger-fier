package tptp;

import java.util.ArrayList;
import java.util.Objects;
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

    @Override
    public String toString() {
        return "TransitionRelation{" +
                "transitions=" + transitions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransitionRelation that = (TransitionRelation) o;
        return Objects.equals(transitions, that.transitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transitions);
    }
}
