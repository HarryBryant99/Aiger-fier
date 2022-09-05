package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerLadderTransformation;
import aiger.AigerTransitionRelationTransformation;
import aiger.And;
import aiger.InputPropositions;
import aiger.Latch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.DeMorganConjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tptp.Transition;
import tptp.TransitionRelation;

public class AigerTransitionRelationTransformationTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("vB")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(4,4,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, vA <=> ~vB)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("vB"))));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new Latch(4,4,0));


        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA <=> vA & vB)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vA"),new Proposition("vB"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 2, 6));
        expectedAig.addComponent(new Latch(6,6,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vA <=> vB & vC)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vC"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 6, 8));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test5(){
        String data = "fof(ax,axiom, vA <=> vB | vE)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_0"))));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(4, 7, 9));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_0"))));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(4, 6, 8));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> (vB & vE))";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 6, 8));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_0"))));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(4, 6, 8));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 6, 8));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 6, 9));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"),new Proposition("gen_1")));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("gen_0")),new Proposition("vC"),new Proposition("gen_1")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(6, 9, 11));
        expectedAig.addComponent(new And(4, 7, 12));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Latch(12,12,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_1"))));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("gen_0")),new Negation(new Proposition("vC")),new Proposition("gen_1")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(6, 8, 10));
        expectedAig.addComponent(new And(4, 7, 13));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Latch(12,12,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_1"))));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("gen_0"),new Negation(new Proposition("vC")),new Proposition("gen_1")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(6, 9, 11));
        expectedAig.addComponent(new And(4, 6, 13));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Latch(12,12,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"),new Proposition("gen_1")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("gen_0"),new Proposition("vC"),new Proposition("gen_1")));
        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(6, 8, 10));
        expectedAig.addComponent(new And(4, 6, 12));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Latch(12,12,0));
        expectedAig.addComponent(new Latch(10,10,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test16(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE)).\n" +
                "fof(ax,axiom, vA <=> vB | vE).";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceTR.addTransition(t);

        Transition t2 = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_1"))));
        t2.addConjunction(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_1")));
        sourceTR.addTransition(t2);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new And(4, 6, 8));

        expectedAig.addComponent(new Latch(2,11,0));
        expectedAig.addComponent(new And(10, 7, 9));
        expectedAig.addComponent(new Latch(6,6,0));
        expectedAig.addComponent(new Latch(8,8,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

    @Test
    public void test19(){
        String data = "fof(ax,axiom, vA <=> ((vB & vC & vD) | (vA & ~ vB & ~ vC) | (vA & ~ vB & vD)))";

        TransitionRelation sourceTR = new TransitionRelation();

        Transition t = new Transition(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_0"))));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("gen_1")),new Negation(new Proposition("gen_2")),new Proposition("gen_0")));
        t.addConjunction(new DeMorganConjunction(new Negation(new Proposition("gen_3")),new Negation(new Proposition("gen_4")),new Proposition("gen_1")));
        t.addConjunction(new DeMorganConjunction(new Proposition("gen_5"),new Proposition("vD"),new Proposition("gen_3")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vB"),new Proposition("vC"),new Proposition("gen_5")));
        t.addConjunction(new DeMorganConjunction(new Proposition("gen_6"),new Negation(new Proposition("vC")),new Proposition("gen_4")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vA"),new Negation(new Proposition("vB")),new Proposition("gen_5")));
        t.addConjunction(new DeMorganConjunction(new Proposition("gen_7"),new Negation(new Proposition("vD")),new Proposition("gen_2")));
        t.addConjunction(new DeMorganConjunction(new Proposition("vA"),new Negation(new Proposition("vB")),new Proposition("gen_7")));

        sourceTR.addTransition(t);

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,5,0));
        expectedAig.addComponent(new And(4, 7, 9));
        expectedAig.addComponent(new And(6, 11, 13));
        expectedAig.addComponent(new And(10, 14, 16));
        expectedAig.addComponent(new And(14, 18, 20));
        expectedAig.addComponent(new And(12, 22, 21));
        expectedAig.addComponent(new And(14, 2, 19));
        expectedAig.addComponent(new And(8, 24, 17));
        expectedAig.addComponent(new And(24, 2, 19));
        expectedAig.addComponent(new Latch(18,18,0));
        expectedAig.addComponent(new Latch(22,22,0));
        expectedAig.addComponent(new Latch(20,20,0));
        expectedAig.addComponent(new Latch(16,16,0));

        AigerTransitionRelationTransformation tt = new AigerTransitionRelationTransformation(null);
        assertEquals(expectedAig, tt.convertRelation(sourceTR));
    }

//    @Test
//    public void test19b() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vA <=> ((vB & vC & vD) | (vA & ~ vB & ~ vC) | (vA & ~ vB & vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_8")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Negation(new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_11")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_13")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Negation(new Proposition("gen_16")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_17")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_14"),new Proposition("gen_21")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,8,1));
//        expectedAig.addComponent(new And(10,2,6));
//        expectedAig.addComponent(new Latch(12,14,1));
//        expectedAig.addComponent(new And(16,10,12));
//        expectedAig.addComponent(new Latch(18,17,0));
//        expectedAig.addComponent(new Latch(20,22,1));
//        expectedAig.addComponent(new Latch(24,4,1));
//        expectedAig.addComponent(new Latch(26,25,0));
//        expectedAig.addComponent(new And(28,20,26));
//        expectedAig.addComponent(new Latch(30,8,1));
//        expectedAig.addComponent(new Latch(32,31,0));
//        expectedAig.addComponent(new And(34,28,32));
//        expectedAig.addComponent(new Latch(36,35,1));
//        expectedAig.addComponent(new And(38,18,36));
//        expectedAig.addComponent(new Latch(40,22,1));
//        expectedAig.addComponent(new Latch(42,4,1));
//        expectedAig.addComponent(new Latch(44,43,0));
//        expectedAig.addComponent(new And(46,40,44));
//        expectedAig.addComponent(new Latch(48,14,1));
//        expectedAig.addComponent(new And(50,46,48));
//        expectedAig.addComponent(new Latch(52,51,1));
//        expectedAig.addComponent(new And(54,38,52));
//        expectedAig.addComponent(new Latch(22,55,1));
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(8,8,1));
//        expectedAig.addComponent(new Latch(14,14,1));
//
//        //expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test20() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vB <=> ((~ vA & vB) | (~ vA & vC & ~ vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Negation(new Proposition("gen_3")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Negation(new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_7")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Negation(new Proposition("gen_9")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_8"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Negation(new Proposition("gen_11")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vB"),new Negation(new Proposition("gen_13")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,13,1));
//
//        expectedAig.addComponent(new Latch(16,4,1));
//        expectedAig.addComponent(new Latch(18,17,0));
//        expectedAig.addComponent(new Latch(20,22,1));
//        expectedAig.addComponent(new And(24,18,20));
//        expectedAig.addComponent(new Latch(26,28,1));
//        expectedAig.addComponent(new Latch(30,27,0));
//        expectedAig.addComponent(new And(32,24,30));
//        expectedAig.addComponent(new Latch(34,33,1));
//
//        expectedAig.addComponent(new And(36,14,34));
//        expectedAig.addComponent(new Latch(10,37,1));
//
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(22,22,1));
//        expectedAig.addComponent(new Latch(28,28,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test21() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vC <=> ((~ vB & vC & ~ vD) | (~ vA & vB & vD) | (vB & vC & vD) | (vA & ~ vB & ~ vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_23"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_24"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_25"),new Negation(new Proposition("gen_24")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_26"),new Conjunction(new Proposition("gen_23"),new Proposition("gen_25")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_27"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_28"),new Negation(new Proposition("gen_27")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_29"),new Conjunction(new Proposition("gen_26"),new Proposition("gen_28")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_30"),new Negation(new Proposition("gen_29")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_31"),new Conjunction(new Proposition("gen_22"),new Proposition("gen_30")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vC"),new Negation(new Proposition("gen_31")))));
//
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,16,1));
//        expectedAig.addComponent(new Latch(18,15,0));
//        expectedAig.addComponent(new And(20,12,18));
//        expectedAig.addComponent(new Latch(22,21,1));
//
//        expectedAig.addComponent(new Latch(24,26,1));
//        expectedAig.addComponent(new Latch(28,25,0));
//        expectedAig.addComponent(new Latch(30,4,1));
//        expectedAig.addComponent(new And(32,28,30));
//        expectedAig.addComponent(new Latch(34,16,1));
//        expectedAig.addComponent(new And(36,32,34));
//        expectedAig.addComponent(new Latch(38,37,1));
//        expectedAig.addComponent(new And(40,22,38));
//
//        expectedAig.addComponent(new Latch(42,4,1));
//        expectedAig.addComponent(new Latch(44,10,1));
//        expectedAig.addComponent(new And(46,42,44));
//        expectedAig.addComponent(new Latch(48,16,1));
//        expectedAig.addComponent(new And(50,46,48));
//        expectedAig.addComponent(new Latch(52,51,0));
//        expectedAig.addComponent(new And(54,40,52));
//
//        expectedAig.addComponent(new Latch(56,26,1));
//        expectedAig.addComponent(new Latch(58,4,1));
//        expectedAig.addComponent(new Latch(60,59,0));
//        expectedAig.addComponent(new And(62,56,60));
//        expectedAig.addComponent(new Latch(64,16,1));
//        expectedAig.addComponent(new Latch(66,65,0));
//        expectedAig.addComponent(new And(68,62,66));
//        expectedAig.addComponent(new Latch(70,69,1));
//        expectedAig.addComponent(new And(72,54,70));
//
//        expectedAig.addComponent(new Latch(10,73,1));
//        expectedAig.addComponent(new Latch(26,26,1));
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(16,16,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test22() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vD <=> ((~ vA & vB & ~ vC) | (~ vA & vB & vD) | (vA & vC & vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vD"),new Negation(new Proposition("gen_22")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,16,1));
//        expectedAig.addComponent(new Latch(18,15,0));
//        expectedAig.addComponent(new And(20,12,18));
//        expectedAig.addComponent(new Latch(22,21,1));
//
//        expectedAig.addComponent(new Latch(24,4,1));
//        expectedAig.addComponent(new Latch(26,25,0));
//        expectedAig.addComponent(new Latch(28,10,1));
//        expectedAig.addComponent(new And(30,26,28));
//        expectedAig.addComponent(new Latch(32,34,1));
//        expectedAig.addComponent(new And(36,30,32));
//        expectedAig.addComponent(new Latch(38,37,1));
//        expectedAig.addComponent(new And(40,22,38));
//
//        expectedAig.addComponent(new Latch(42,4,1));
//        expectedAig.addComponent(new Latch(44,16,1));
//        expectedAig.addComponent(new And(46,42,44));
//        expectedAig.addComponent(new Latch(48,34,1));
//        expectedAig.addComponent(new And(50,46,48));
//        expectedAig.addComponent(new Latch(52,51,0));
//        expectedAig.addComponent(new And(54,40,52));
//        expectedAig.addComponent(new Latch(34,55,1));
//
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(10,10,1));
//        expectedAig.addComponent(new Latch(16,16,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void checkifupdatinginputvariablesiscausingproblems() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vA <=> ~vA)" +
//                "fof(ax,axiom, vB <=> vA)";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("vA")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vB"),new Proposition("vA"))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,3,1));
//        expectedAig.addComponent(new Latch(4,2,1));
//
//        //expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test23A() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vA <=> ((vA & ~vC) | (vA & vD) | (vA & vB) | (vB & vC & vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Negation(new Proposition("gen_1")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Negation(new Proposition("gen_3")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_6")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_8")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_10"),new Proposition("gen_11")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_13")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_16")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Conjunction(new Proposition("gen_17"),new Proposition("gen_18")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Negation(new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Conjunction(new Proposition("gen_14"),new Proposition("gen_20")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_21")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,8,1));
//        expectedAig.addComponent(new And(10,2,6));
//
//        //expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test23B() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vB <=> ((~ vA & vB) | (~ vA & vC & ~ vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Negation(new Proposition("gen_3")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Negation(new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_7")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Negation(new Proposition("gen_9")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_8"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Negation(new Proposition("gen_11")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_12")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vB"),new Negation(new Proposition("gen_13")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,13,1));
//
//        expectedAig.addComponent(new Latch(16,4,1));
//        expectedAig.addComponent(new Latch(18,17,0));
//        expectedAig.addComponent(new Latch(20,22,1));
//        expectedAig.addComponent(new And(24,18,20));
//        expectedAig.addComponent(new Latch(26,28,1));
//        expectedAig.addComponent(new Latch(30,27,0));
//        expectedAig.addComponent(new And(32,24,30));
//        expectedAig.addComponent(new Latch(34,33,1));
//
//        expectedAig.addComponent(new And(36,14,34));
//        expectedAig.addComponent(new Latch(10,37,1));
//
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(22,22,1));
//        expectedAig.addComponent(new Latch(28,28,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test23C() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vC <=> ((~ vB & vC & ~ vD) | (~ vA & vB & vD) | (vB & vC & vD) | (vA & ~ vB & ~ vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_23"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_24"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_25"),new Negation(new Proposition("gen_24")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_26"),new Conjunction(new Proposition("gen_23"),new Proposition("gen_25")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_27"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_28"),new Negation(new Proposition("gen_27")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_29"),new Conjunction(new Proposition("gen_26"),new Proposition("gen_28")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_30"),new Negation(new Proposition("gen_29")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_31"),new Conjunction(new Proposition("gen_22"),new Proposition("gen_30")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vC"),new Negation(new Proposition("gen_31")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,16,1));
//        expectedAig.addComponent(new Latch(18,15,0));
//        expectedAig.addComponent(new And(20,12,18));
//        expectedAig.addComponent(new Latch(22,21,1));
//
//        expectedAig.addComponent(new Latch(24,26,1));
//        expectedAig.addComponent(new Latch(28,25,0));
//        expectedAig.addComponent(new Latch(30,4,1));
//        expectedAig.addComponent(new And(32,28,30));
//        expectedAig.addComponent(new Latch(34,16,1));
//        expectedAig.addComponent(new And(36,32,34));
//        expectedAig.addComponent(new Latch(38,37,1));
//        expectedAig.addComponent(new And(40,22,38));
//
//        expectedAig.addComponent(new Latch(42,4,1));
//        expectedAig.addComponent(new Latch(44,10,1));
//        expectedAig.addComponent(new And(46,42,44));
//        expectedAig.addComponent(new Latch(48,16,1));
//        expectedAig.addComponent(new And(50,46,48));
//        expectedAig.addComponent(new Latch(52,51,0));
//        expectedAig.addComponent(new And(54,40,52));
//
//        expectedAig.addComponent(new Latch(56,26,1));
//        expectedAig.addComponent(new Latch(58,4,1));
//        expectedAig.addComponent(new Latch(60,59,0));
//        expectedAig.addComponent(new And(62,56,60));
//        expectedAig.addComponent(new Latch(64,16,1));
//        expectedAig.addComponent(new Latch(66,65,0));
//        expectedAig.addComponent(new And(68,62,66));
//        expectedAig.addComponent(new Latch(70,69,1));
//        expectedAig.addComponent(new And(72,54,70));
//
//        expectedAig.addComponent(new Latch(10,73,1));
//        expectedAig.addComponent(new Latch(26,26,1));
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(16,16,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void test23D() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vD <=> ((~ vA & vB & ~ vC) | (~ vA & vB & vD) | (vA & vC & vD)))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vA"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vD"),new Negation(new Proposition("gen_22")))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,3,0));
//        expectedAig.addComponent(new Latch(8,10,1));
//        expectedAig.addComponent(new And(12,6,8));
//        expectedAig.addComponent(new Latch(14,16,1));
//        expectedAig.addComponent(new Latch(18,15,0));
//        expectedAig.addComponent(new And(20,12,18));
//        expectedAig.addComponent(new Latch(22,21,1));
//
//        expectedAig.addComponent(new Latch(24,4,1));
//        expectedAig.addComponent(new Latch(26,25,0));
//        expectedAig.addComponent(new Latch(28,10,1));
//        expectedAig.addComponent(new And(30,26,28));
//        expectedAig.addComponent(new Latch(32,34,1));
//        expectedAig.addComponent(new And(36,30,32));
//        expectedAig.addComponent(new Latch(38,37,1));
//        expectedAig.addComponent(new And(40,22,38));
//
//        expectedAig.addComponent(new Latch(42,4,1));
//        expectedAig.addComponent(new Latch(44,16,1));
//        expectedAig.addComponent(new And(46,42,44));
//        expectedAig.addComponent(new Latch(48,34,1));
//        expectedAig.addComponent(new And(50,46,48));
//        expectedAig.addComponent(new Latch(52,51,0));
//        expectedAig.addComponent(new And(54,40,52));
//        expectedAig.addComponent(new Latch(34,55,1));
//
//        expectedAig.addComponent(new Latch(4,4,1));
//        expectedAig.addComponent(new Latch(10,10,1));
//        expectedAig.addComponent(new Latch(16,16,1));
//
//        File input = new File("ladder_logic_examples/Example3Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
//
//    @Test
//    public void testExample5() throws FileNotFoundException {
//        String data = "fof(ax,axiom, vA <=> ~(((~(va & ~vc) & ~(va & vd)) & ~(va & vb)) & ~(vb & vc & vd))";
//
//        Ladder sourceL = new Ladder();
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("va"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vc"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Negation(new Proposition("gen_1")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_0"), new Proposition("gen_2")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Negation(new Proposition("gen_3")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("va"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vd"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Conjunction(new Proposition("gen_5"), new Proposition("gen_6")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_4"), new Proposition("gen_8")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("va"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Proposition("vb"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_10"), new Proposition("gen_11")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_9"), new Proposition("gen_13")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vb"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vc"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Conjunction(new Proposition("gen_15"), new Proposition("gen_16")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Proposition("vd"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Conjunction(new Proposition("gen_17"), new Proposition("gen_18")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Negation(new Proposition("gen_19")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Conjunction(new Proposition("gen_14"), new Proposition("gen_20")))));
//
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("va"),new Negation(new Proposition("gen_21")))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vb"),new Proposition("vb"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vc"),new Proposition("vc"))));
//        sourceL.addRung(new Rung(new Equivalence(new Proposition("vd"),new Proposition("vd"))));
//
//        // TODO: Calculate real expected result
//        Aig expectedAig = new Aig();
//        expectedAig.addComponent(new Latch(2,4,1));
//        expectedAig.addComponent(new Latch(6,8,1));
//        expectedAig.addComponent(new Latch(10,7,0));
//        expectedAig.addComponent(new And(12,2,10));
//        expectedAig.addComponent(new Latch(14,13,1));
//
//        expectedAig.addComponent(new Latch(16,4,1));
//        expectedAig.addComponent(new Latch(18,20,1));
//        expectedAig.addComponent(new And(22,16,18));
//        expectedAig.addComponent(new Latch(24,23,0));
//        expectedAig.addComponent(new And(26,14,24));
//
//        expectedAig.addComponent(new Latch(28,4,1));
//        expectedAig.addComponent(new Latch(30,32,1));
//        expectedAig.addComponent(new And(34,28,30));
//        expectedAig.addComponent(new Latch(36,35,0));
//        expectedAig.addComponent(new And(38,26,36));
//
//        expectedAig.addComponent(new Latch(40,32,1));
//        expectedAig.addComponent(new Latch(42,8,1));
//        expectedAig.addComponent(new And(44,40,42));
//        expectedAig.addComponent(new Latch(46,20,1));
//        expectedAig.addComponent(new And(48,44,46));
//        expectedAig.addComponent(new Latch(50,49,0));
//        expectedAig.addComponent(new And(52,38,50));
//
//        expectedAig.addComponent(new Latch(4,53,1));
//        expectedAig.addComponent(new Latch(32,32,1));
//        expectedAig.addComponent(new Latch(8,8,1));
//        expectedAig.addComponent(new Latch(20,20,1));
//
//        File input = new File("ladder_logic_examples/Example5Inputs.txt");
//        InputStream in = new FileInputStream(input);
//        InputPropositions iv = new InputPropositions(in);
//
//        AigerLadderTransformation tt = new AigerLadderTransformation(iv.getHashMap());
//        assertEquals(expectedAig, tt.convertLadder(sourceL));
//    }
}
