package main;

import aiger.AigerPrinter;
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
        File f = new File("Test.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);

        File input = new File("input.txt");
        in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        TseitinTransformation tt = new TseitinTransformation();
        AigerPrinter aig = new AigerPrinter();
        System.out.println(aig.convertLadder(tt.transform(l),iv.getHashMap()));
    }
}
