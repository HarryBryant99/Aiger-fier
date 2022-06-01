package aiger;

import java.util.List;

public class Aig {
    private List<String> aigerComponents;

    public Aig() {
    }

    public void addComponent(String component){
        aigerComponents.add(component);
    }

    public List<String> getAigerComponents() {
        return aigerComponents;
    }
}
