package tptp;

import java.util.ArrayList;
import prop_logic.Conjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;

public class Transition {
    private Equivalence equiv;
    private ArrayList<Conjunction> conjunctions = new ArrayList<>();

    public Transition(Equivalence equiv, ArrayList<Conjunction> conjunctions) {
        this.equiv = equiv;
        this.conjunctions = conjunctions;
    }

    public Equivalence getEquiv() {
        return equiv;
    }

    public ArrayList<Conjunction> getConjunctions() {
        return conjunctions;
    }
}
