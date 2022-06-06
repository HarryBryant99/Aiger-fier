package aiger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import prop_logic.Equivalence;
import prop_logic_parser.Parser;
import tptp.Rung;

public class InputPropositions {

    private HashMap<String, Integer> initalVariableValues = new HashMap<String, Integer>();

    public InputPropositions(InputStream in) {
        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)))){
            //s.useDelimiter(" ");
            while (s.hasNextLine()){
                String proposition = s.next();

                if (proposition.trim().isEmpty()){
                    continue;
                }

                Integer inputValue = s.nextInt();

                addProposition(proposition, inputValue);
            }
        }
    }

    private Integer getInitialValue(String proposition){
        return initalVariableValues.get(proposition);
    }

    private void addProposition(String proposition, Integer initialValue){
        initalVariableValues.put(proposition, initialValue);
    }

    private boolean isExistingProposition(String proposition){
        return initalVariableValues.containsKey(proposition);
    }

    public HashMap<String, Integer> getHashMap(){
        return initalVariableValues;
    }

}
