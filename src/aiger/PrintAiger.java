package aiger;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Proposition;
import tptp.Rung;

public class PrintAiger {
    private Aig aig;
    private List<Latch> latches;
    private Output output;
    private List<And> ands;

    public PrintAiger(Aig aig) {
        this.aig = aig;
    }

    private Aig sortAig(){
        for (AigerComponent c : aig.getComponents()) {
            splitComponents(c);
        }

        ArrayList<AigerComponent> components = null;
        components.addAll(latches);
        components.add(output);
        components.addAll(ands);

        aig.setAigerComponents(components);

        return aig;
    }

    private void splitComponents(AigerComponent c){
        if (c.getClass() == Latch.class){
            latches.add((Latch) c);
        } else if (c.getClass() == And.class){
            ands.add((And) c);
        } else if (c.getClass() == Output.class){
            output = (Output) c;
        } else {
            throw new IllegalStateException("What is this sub type?");
        }
    }

    private void printAig() {
        System.out.println("aag 0 " + latches.size() + " 0 " + ands.size() + " 1");

        for (AigerComponent l: latches) {
            System.out.println(l.toString());
        }

        System.out.println(output.toString());

        for (AigerComponent a: ands) {
            System.out.println(a.toString());
        }
    }
}
