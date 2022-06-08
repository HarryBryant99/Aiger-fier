package main;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.InputPropositions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import tptp.Ladder;
import tptp.LadderParser;
import tseitin_transformation.TseitinTransformation;

public class main {
    public static void main(String[] args) throws IOException {
        File f = new File("Test2.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File safetyFile = new File("Safety.tptp");
        in = new FileInputStream(safetyFile);
        Ladder safety = LadderParser.parseLadder(in);

        File input = new File("input.txt");
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        TseitinTransformation tt = new TseitinTransformation();
        AigerTransformation aig = new AigerTransformation(iv.getHashMap());

        Aig newAiger = aig.convertLadder(tt.transform(l));

        Ladder transformed = tt.transform(safety);
        newAiger.addAllComponents(aig.addSafetyProperty(transformed));

        System.out.println(newAiger);
    }
}
