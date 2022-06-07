package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerPrinter;
import aiger.InputPropositions;
import aiger.Latch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class InputPropostionsTest {
    @Test
    public void test1() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedAig, tt.convertLadder(sourceL,iv.getHashMap()));
    }

    @Test
    public void test2() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vC"))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedAig, tt.convertLadder(sourceL,iv.getHashMap()));
    }

    @Test
    public void test3() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> ~vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_0")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedAig, tt.convertLadder(sourceL,iv.getHashMap()));
    }
}
