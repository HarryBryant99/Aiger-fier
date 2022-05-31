package tptp;

import java.util.ArrayList;
import tptp.Rung;

public class Ladder {

    private ArrayList<Rung> rungs = new ArrayList<>();

    public Ladder() {
    }

    public void addRung(Rung newRung){
        rungs.add(newRung);
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "rungs=" + rungs +
                '}';
    }
}
