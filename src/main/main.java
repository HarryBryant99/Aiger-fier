package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import tptp.Ladder;
import tptp.LadderParser;

public class main {
    public static void main(String[] args) throws IOException {
        File f = new File("Test.tptp");
        InputStream in = new FileInputStream(f);
        Ladder l = LadderParser.parseLadder(in);
    }
}
