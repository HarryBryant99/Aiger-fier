package tptp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import prop_logic.Conjunction;
import prop_logic.DeMorganConjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;

public class Transition {
    private Equivalence equiv;
    private ArrayList<DeMorganConjunction> conjunctions = new ArrayList<>();

    public Transition(Equivalence equiv) {
        this.equiv = equiv;
    }

    public Transition() {
    }

    public void setEquiv(Equivalence equiv) {
        this.equiv = equiv;
    }

    public void setConjunctions(ArrayList<DeMorganConjunction> conjunctions) {
        this.conjunctions = conjunctions;
    }

    public void addConjunction(DeMorganConjunction newConjunction){
        Objects.nonNull(newConjunction);
        conjunctions.add(newConjunction);
    }

    public void addAllConjunctions(List<DeMorganConjunction> newConjunctions){
        Objects.nonNull(newConjunctions);
        conjunctions.addAll(newConjunctions);
    }

    public Equivalence getEquiv() {
        return equiv;
    }

    public ArrayList<DeMorganConjunction> getConjunctions() {
        return conjunctions;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "equiv=" + equiv +
                ", conjunctions=" + conjunctions +
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
        Transition that = (Transition) o;
        return Objects.equals(equiv, that.equiv) &&
                Objects.equals(conjunctions, that.conjunctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equiv, conjunctions);
    }
}
