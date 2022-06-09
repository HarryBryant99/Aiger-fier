package main;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.InputPropositions;
import aiger.PrintAiger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.omg.CORBA.StringHolder;
import tptp.Ladder;
import tptp.LadderParser;
import tseitin_transformation.TseitinTransformation;

public class main {
    public static void main(String[] args) throws IOException {
        //TODO argparse

        File f = new File("Example1.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File safetyFile = new File("Example1Safety.tptp");
        in = new FileInputStream(safetyFile);
        Ladder safety = LadderParser.parseLadder(in);

        File input = new File("Example1Inputs.txt");
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        TseitinTransformation tt = new TseitinTransformation();
        AigerTransformation aig = new AigerTransformation(iv.getHashMap());

        Aig newAiger = aig.convertLadder(tt.transform(l));

        Ladder transformed = tt.transform(safety);
        newAiger.addAllComponents(aig.addSafetyProperty(transformed));

        System.out.println(newAiger);

        PrintAiger printer = new PrintAiger(newAiger);
        printer.printAig();
    }
}
