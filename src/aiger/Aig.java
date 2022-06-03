package aiger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import prop_logic.Equivalence;
import tptp.Rung;

public class Aig {
    private ArrayList<AigerComponent> aigerComponents = new ArrayList<>();

    public Aig() {
    }

    public void addComponent(AigerComponent component){
        aigerComponents.add(component);
    }

    @Override
    public String toString() {
        return "Aig{" +
                "aigerComponents=" + aigerComponents +
                '}';
    }

    public ArrayList<AigerComponent> getComponents(){
        return aigerComponents;
    }
}
