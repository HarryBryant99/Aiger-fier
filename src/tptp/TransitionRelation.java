package tptp;

import java.util.ArrayList;
import prop_logic.Conjunction;
import prop_logic.Expression;

public class TransitionRelation {
    private String id;
    private Expression latchValue;
    private ArrayList<Conjunction> conjunctions = new ArrayList<>();

    public TransitionRelation(String id, Expression latchValue, ArrayList<Conjunction> conjunctions) {
        this.id = id;
        this.latchValue = latchValue;
        this.conjunctions = conjunctions;
    }

    public String getId() {
        return id;
    }

    public Expression getLatchValue() {
        return latchValue;
    }

    public ArrayList<Conjunction> getConjunctions() {
        return conjunctions;
    }
}
