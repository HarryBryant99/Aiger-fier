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
    private ArrayList<String> aigerComponents = new ArrayList<>();

    public Aig() {
    }

    public void addComponent(String component){
        aigerComponents.add(component);
    }

    public String toString(){
        String result = "";
        for (String r : Collections.unmodifiableList(aigerComponents)) {
            result += r + "\n";
        }
        return result;
    }

    public ArrayList<String> getComponents(){
        return aigerComponents;
    }
}
