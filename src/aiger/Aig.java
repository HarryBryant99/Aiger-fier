package aiger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import prop_logic.Equivalence;
import tptp.Rung;

public class Aig {
    private ArrayList<AigerComponent> aigerComponents = new ArrayList<>();

    public Aig() {
    }

    public void addComponent(AigerComponent component){
        aigerComponents.add(component);
    }

    public void addAllComponents(List<AigerComponent> components){
        aigerComponents.addAll(components);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aig aig = (Aig) o;
        return aigerComponents.equals(aig.aigerComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aigerComponents);
    }
}
