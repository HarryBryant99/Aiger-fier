package tptp;

import java.util.ArrayList;
import java.util.Objects;
import javax.jws.Oneway;
import tptp.Rung;

public class Ladder {

    private ArrayList<Rung> rungs = new ArrayList<>();

    public Ladder() {
    }

    public void addRung(Rung newRung){
        Objects.nonNull(newRung);
        rungs.add(newRung);
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "rungs=" + rungs +
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
        Ladder ladder = (Ladder) o;
        return rungs.equals(ladder.rungs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rungs);
    }
}
