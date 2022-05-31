package tptp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import prop_logic.Expression;
import prop_logic_parser.Parser;

public class LadderParser {
    private static final String prefix = "fof(ax,axiom, ";
    private static final String postfix = ").";

    public static Ladder parseLadder(InputStream in) throws IOException {

        Ladder l = new Ladder();

        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)))){
            while (s.hasNextLine()){
                String line = s.nextLine();

                if (line.trim().isEmpty()){
                    continue;
                }

                assert line.startsWith(prefix);
                assert line.endsWith(postfix);
                String lineBody = line.substring(prefix.length(), line.length() - postfix.length());

                Expression exp = Parser.parse(lineBody);
                l.addRung(new Rung(exp));
            }
        }

        return l;
    }
}
