package main;

import aiger.Aig;
import aiger.AigerLadderTransformation;
import aiger.AigerTransitionRelationTransformation;
import aiger.InputPropositions;
import aiger.PrintAiger;
import demorgantransformation.DeMorganTransformation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.SafetyCondition;
import tptp.SafetyConditionParser;
import tseitin_transformation.TseitinTransformation;

public class main {
    public static void mainOld(String[] args) throws IOException {
        //TODO argparse

        File f = new File("ladder_logic_examples/Example1.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File safetyFile = new File("ladder_logic_examples/Example1Safety.tptp");
        in = new FileInputStream(safetyFile);
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        File input = new File("ladder_logic_examples/Example1Inputs.txt");
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        DeMorganTransformation dmt = new DeMorganTransformation();
        //TseitinTransformation tt = new TseitinTransformation();
        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        //AigerLadderTransformation aig = new AigerLadderTransformation(iv.getHashMap());
        AigerTransitionRelationTransformation aig = new AigerTransitionRelationTransformation(iv.getHashMap());

        //System.out.println(iv.getHashMap());

        //Aig newAiger = aig.convertLadder(tt.transform(l));
        Aig newAiger = aig.convertRelation(dmt.transform(l));
        Aig scAiger = aig.convertSafetyCondition(sct.transform(sc));

        newAiger.addAllComponents(scAiger.getComponents());

        //System.out.println(tt.transform(l));

        //System.out.println("\n"+newAiger.getComponents()+"\n");

        PrintAiger printer = new PrintAiger(newAiger);
        printer.printAig();
        printer.writeAiger("newAiger", "aiger.aag", "folder");

        System.out.println("\nNumber of variables: " + aig.getNumberOfVariables());
    }

    public static void main(String[] args) throws IOException {
        //TODO argparse
        String ladder = args[0];
        String initial = args[1];

        File f = new File(ladder);
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File input = new File(initial);
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        File folder = new File(args[2]);
        ArrayList<String> safetyProperties = listFilesForFolder(folder);

        for (String p:safetyProperties) {
            DeMorganTransformation dmt = new DeMorganTransformation();
            AigerTransitionRelationTransformation aig = new AigerTransitionRelationTransformation(iv.getHashMap());
            Aig transitions = aig.convertRelation(dmt.transform(l));

            File safetyFile = new File(args[2] + "\\" + p);
            in = new FileInputStream(safetyFile);

            //System.out.println(p);

            SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

            SafetyConditionTransformation sct = new SafetyConditionTransformation();
            Aig scAiger = aig.convertSafetyCondition(sct.transform(sc));

            Aig newAiger = new Aig();
            newAiger.addAllComponents(transitions.getComponents());
            newAiger.addAllComponents(scAiger.getComponents());

            PrintAiger printer = new PrintAiger(newAiger);
            printer.printAig();

            String ladderFormatted = nameOfLadder(ladder);
            String folderFormatted = nameOfFolder(args[2]);

            printer.writeAiger(ladderFormatted, filename(ladderFormatted, p), folderFormatted);
        }
    }

    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                //System.out.println(fileEntry.getName());
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    private static String filename(String ladder, String p){
        String safetyFormatted = p.substring(0, p.length()-5);
        return ladder + "_" + safetyFormatted + ".aag";
    }

    private static String nameOfLadder(String ladder){
        String ladderFormatted = "";

        for (int i = ladder.length()-1; i > 0; i--) {
            if (ladder.charAt(i) == '\\') {
                ladderFormatted = ladder.substring(i+1, ladder.length()-5);
                break;
            }
        }

        return ladderFormatted;
    }

    private static String nameOfFolder(String folder){
        String folderFormatted = "";

        for (int i = folder.length()-1; i > 0; i--) {
            if (folder.charAt(i) == '\\') {
                folderFormatted = folder.substring(i+1, folder.length());
                break;
            }
        }

        return folderFormatted;
    }
}
