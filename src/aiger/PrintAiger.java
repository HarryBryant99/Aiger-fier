package aiger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import prop_logic.Proposition;
import tptp.Rung;

public class PrintAiger {
    private Aig aig;
    private ArrayList<Latch> latches = new ArrayList<>();
    private Output output;
    private ArrayList<And> ands = new ArrayList<>();

    public PrintAiger(Aig aig) {
        this.aig = aig;
    }

    private Aig sortAig(){
        for (AigerComponent c : aig.getComponents()) {
            splitComponents(c);
        }

        ArrayList<AigerComponent> components = new ArrayList<>();
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

    public void printAig() {
        sortAig();

        System.out.println("aag " + getVars() + " 0 " + latches.size() + " 0 " + ands.size() + " 1");

        for (Latch l: latches) {
            System.out.println(l.print());
        }

        System.out.println(output.print());

        for (And a: ands) {
            System.out.println(a.print());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrintAiger that = (PrintAiger) o;
        return aig.equals(that.aig) && latches.equals(that.latches) && output.equals(that.output) &&
                ands.equals(that.ands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aig, latches, output, ands);
    }

    public void writeAiger() throws IOException {
        File yourFile = new File("aiger.aag");
        yourFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(yourFile, false);

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("aiger.aag");

            fileWriter.write("aag " + getVars() + " 0 " + latches.size() + " 0 " + ands.size() + " 1\n");

            for (Latch l: latches) {
                fileWriter.write(l.print() + "\n");
            }

            fileWriter.write(output.print() + "\n");

            for (And a: ands) {
                fileWriter.write(a.print() + "\n");
            }

            fileWriter.close(); //Closes the fileWriter

        } catch (FileNotFoundException e) { // If the file does not exist, handles problem.
            System.err.println("File newFile.txt does not exist.");
            System.exit(-1);
        } catch (IOException e){ // If there was a problem writing, gives feedback to the user.
            System.err.println("Caught IO error, writing to newFile.txt");
            System.exit(-1);
        }
    }

    public int getVars() {
        int maxVar = 0;
        for (Latch l : latches) {
            if (l.getId() > maxVar){
                maxVar = l.getId();
            }
        }

        for (And a : ands) {
            if (a.getId() > maxVar){
                maxVar = a.getId();
            }
        }
        return maxVar/2;
    }
}
