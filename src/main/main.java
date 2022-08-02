package main;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.InputPropositions;
import aiger.Output;
import aiger.PrintAiger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.SafetyCondition;
import tptp.SafetyConditionParser;
import tseitin_transformation.TseitinTransformation;

public class main {
    public static void main(String[] args) throws IOException {
        //TODO argparse

        File f = new File("ladder_logic_examples/tom_werner/Tom_Ladder.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File safetyFile = new File("ladder_logic_examples/tom_werner/Tom_Safety_20.tptp");
        in = new FileInputStream(safetyFile);
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        File input = new File("ladder_logic_examples/tom_werner/Tom_Inputs.txt");
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        TseitinTransformation tt = new TseitinTransformation();
        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        AigerTransformation aig = new AigerTransformation(iv.getHashMap());

        Aig newAiger = aig.convertLadder(tt.transform(l));
        Aig scAiger = aig.convertSafetyCondition(sct.transform(sc));

        newAiger.addAllComponents(scAiger.getComponents());

        System.out.println(tt.transform(l));

        PrintAiger printer = new PrintAiger(newAiger);
        printer.printAig();
        printer.writeAiger();

        System.out.println("\nNumber of variables: " + aig.getNumberOfVariables());
    }
}
