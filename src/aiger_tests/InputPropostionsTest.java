package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerLadderTransformation;
import aiger.And;
import aiger.InputPropositions;
import aiger.Latch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
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
        expectedAig.addComponent(new Latch(4,4,1));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));

        System.out.println(iv.getHashMap());
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
        expectedAig.addComponent(new Latch(4,4,0));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
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
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(4,4,1));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test4() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vA & vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,8,1));
        expectedAig.addComponent(new And(4,2,6));
        expectedAig.addComponent(new Latch(8,8,1));


        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test5() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(4,4,1));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test6() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vB | vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_4")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,1));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(16,15,1));
        expectedAig.addComponent(new Latch(4,4,1));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test7() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> ~(vB & vC))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"), new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"), new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),
                new Conjunction(new Proposition("gen_0"), new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_2")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2, 4, 1));
        expectedAig.addComponent(new Latch(6, 8, 0));
        expectedAig.addComponent(new And(10, 2, 6));
        expectedAig.addComponent(new Latch(12, 11, 1));
        expectedAig.addComponent(new Latch(4, 4, 1));
        expectedAig.addComponent(new Latch(8, 8, 0));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test8() throws FileNotFoundException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> ~(vB & ~vC))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"), new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"), new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"), new Negation(new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),
                new Conjunction(new Proposition("gen_0"), new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_3")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2, 4, 1));
        expectedAig.addComponent(new Latch(6, 8, 0));
        expectedAig.addComponent(new Latch(10, 7, 1));
        expectedAig.addComponent(new And(12, 2, 10));
        expectedAig.addComponent(new Latch(14, 13, 0));
        expectedAig.addComponent(new Latch(4, 4, 1));
        expectedAig.addComponent(new Latch(8, 8, 0));

        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }
}
