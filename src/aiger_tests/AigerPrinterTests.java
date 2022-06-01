package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.AigerPrinter;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tseitin_transformation.TseitinTransformation;

public class AigerPrinterTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedL, tt.convertLadder(sourceL));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, vA <=> ~vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_0")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_0")))));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedL, tt.convertLadder(sourceL));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        AigerPrinter tt = new AigerPrinter();
        assertEquals(expectedL, tt.convertLadder(sourceL));
    }
}
