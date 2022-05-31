package tptp;

import java.util.Objects;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Proposition;

/**
 * A rung has an expression of the form "a <=> F".
 */
public class Rung {

    private Equivalence equivalence;

    public Rung(Equivalence equivalence) {
        assert equivalence != null;

        if (equivalence.getLhsOperand().getClass() != Proposition.class){
            throw new IllegalArgumentException("Root equivalence must have a proposition on lhs");
        }

        this.equivalence = equivalence;
    }

    @Override
    public String toString() {
        return "Rung{" + equivalence + "}";
    }

        public Equivalence getEquivalence(){
        return equivalence;
    }

//    public Proposition getLhsProposition(){
//        return (Proposition) equivalence.getLhsOperand();
//    }
//
//    public Expression getRhsProposition(){
//        return equivalence.getRhsOperand();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rung rung = (Rung) o;
        return equivalence.equals(rung.equivalence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equivalence);
    }
}
