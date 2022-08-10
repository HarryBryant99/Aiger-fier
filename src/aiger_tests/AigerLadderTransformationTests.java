package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.And;
import aiger.InputPropositions;
import aiger.Latch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tseitin_transformation.TseitinTransformation;

public class AigerLadderTransformationTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(4,4,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, vA <=> ~vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_0")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(4,4,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA <=> vA & vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(4,2,6));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test5(){
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
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,0));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(16,15,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_2")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,11,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> (vB & vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_2")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,11,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,0));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Negation(new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_2")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new Latch(10,7,0));
        expectedAig.addComponent(new And(12,2,10));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_6")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,0));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(16,15,0));
        expectedAig.addComponent(new Latch(18,20,0));
        expectedAig.addComponent(new And(22,16,18));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(20,20,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_6")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,11,0));
        expectedAig.addComponent(new Latch(14,16,0));
        expectedAig.addComponent(new Latch(18,15,0));
        expectedAig.addComponent(new And(20,12,18));
        expectedAig.addComponent(new Latch(22,21,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(16,16,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Negation(new Proposition("gen_5")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_6")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_7")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,0));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(16,18,0));
        expectedAig.addComponent(new Latch(20,17,0));
        expectedAig.addComponent(new And(22,14,20));
        expectedAig.addComponent(new Latch(24,23,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(18,18,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,14,0));
        expectedAig.addComponent(new And(16,10,12));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(14,14,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test16(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE)).\n" +
                "fof(ax,axiom, vA <=> vB | vE).";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));

        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vE"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_7")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_8")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();

        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,0));
        expectedAig.addComponent(new And(14,6,12));

        expectedAig.addComponent(new Latch(16,4,0));
        expectedAig.addComponent(new Latch(18,17,0));
        expectedAig.addComponent(new Latch(20,10,0));
        expectedAig.addComponent(new Latch(22,21,0));
        expectedAig.addComponent(new And(24,18,22));
        expectedAig.addComponent(new Latch(14,25,0));

        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test19(){
        String data = "fof(ax,axiom, vA <=> ((vB & vC & vD) | (vA & ~ vB & ~ vC) | (vA & ~ vB & vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vD"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_8")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Negation(new Proposition("gen_10")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_11")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_13")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Negation(new Proposition("gen_16")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_17")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_14"),new Proposition("gen_21")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,14,0));
        expectedAig.addComponent(new And(16,10,12));
        expectedAig.addComponent(new Latch(18,17,0));
        expectedAig.addComponent(new Latch(20,22,0));
        expectedAig.addComponent(new Latch(24,4,0));
        expectedAig.addComponent(new Latch(26,25,0));
        expectedAig.addComponent(new And(28,20,26));
        expectedAig.addComponent(new Latch(30,8,0));
        expectedAig.addComponent(new Latch(32,31,0));
        expectedAig.addComponent(new And(34,28,32));
        expectedAig.addComponent(new Latch(36,35,0));
        expectedAig.addComponent(new And(38,18,36));
        expectedAig.addComponent(new Latch(40,22,0));
        expectedAig.addComponent(new Latch(42,4,0));
        expectedAig.addComponent(new Latch(44,43,0));
        expectedAig.addComponent(new And(46,40,44));
        expectedAig.addComponent(new Latch(48,14,0));
        expectedAig.addComponent(new And(50,46,48));
        expectedAig.addComponent(new Latch(52,51,0));
        expectedAig.addComponent(new And(54,38,52));
        expectedAig.addComponent(new Latch(22,55,0));
        expectedAig.addComponent(new Latch(4,4,0));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Latch(14,14,0));

        //expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }

    @Test
    public void test19b() throws FileNotFoundException {
        String data = "fof(ax,axiom, vA <=> ((vB & vC & vD) | (vA & ~ vB & ~ vC) | (vA & ~ vB & vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vD"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_8")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vC"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Negation(new Proposition("gen_10")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_11")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_13")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vA"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Negation(new Proposition("gen_16")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_17")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_14"),new Proposition("gen_21")))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,8,1));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(12,14,1));
        expectedAig.addComponent(new And(16,10,12));
        expectedAig.addComponent(new Latch(18,17,0));
        expectedAig.addComponent(new Latch(20,22,1));
        expectedAig.addComponent(new Latch(24,4,1));
        expectedAig.addComponent(new Latch(26,25,0));
        expectedAig.addComponent(new And(28,20,26));
        expectedAig.addComponent(new Latch(30,8,1));
        expectedAig.addComponent(new Latch(32,31,0));
        expectedAig.addComponent(new And(34,28,32));
        expectedAig.addComponent(new Latch(36,35,1));
        expectedAig.addComponent(new And(38,18,36));
        expectedAig.addComponent(new Latch(40,22,1));
        expectedAig.addComponent(new Latch(42,4,1));
        expectedAig.addComponent(new Latch(44,43,0));
        expectedAig.addComponent(new And(46,40,44));
        expectedAig.addComponent(new Latch(48,14,1));
        expectedAig.addComponent(new And(50,46,48));
        expectedAig.addComponent(new Latch(52,51,1));
        expectedAig.addComponent(new And(54,38,52));
        expectedAig.addComponent(new Latch(22,55,0));
        expectedAig.addComponent(new Latch(4,4,1));
        expectedAig.addComponent(new Latch(8,8,1));
        expectedAig.addComponent(new Latch(14,14,1));

        //expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));

        File input = new File("ladder_logic_examples/Example3Inputs.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        AigerTransformation tt = new AigerTransformation(iv.getHashMap());
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }
}
