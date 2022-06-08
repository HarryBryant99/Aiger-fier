package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.And;
import aiger.Latch;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class AigerTransformationTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));

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
        expectedAig.addComponent(new Latch(2,4,0));
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

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertLadder(sourceL));
    }
}
