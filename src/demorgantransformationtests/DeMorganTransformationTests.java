package demorgantransformationtests;

import static org.junit.Assert.assertEquals;

import demorgantransformation.DeMorganTransformation;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;
import tseitin_transformation.TseitinTransformation;

public class DeMorganTransformationTests {

    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, vA <=> ~vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("vB")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("vB")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA <=> vBanana_1-)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vBanana_1-"))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vBanana_1-"))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test5(){
        String data = "fof(ax,axiom, vA <=> vB | vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Negation(new Proposition("vB")), new Negation(new Proposition("vE")))))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Proposition("vB"), new Proposition("vE"))))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> (vB & vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Proposition("vB"),new Proposition("vE"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Proposition("vB"),new Proposition("vE"))))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE"))))));


        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Negation(new Proposition("vB")),new Proposition("vE"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Negation(new Proposition("vE"))))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Negation(new Conjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")))),new Proposition("vc")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_6")))));
        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Negation(new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_6")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"), new Negation(new Proposition("gen_7")))));
        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));
        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test16(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE)).\n" +
                "fof(ax,axiom, vA <=> vB | vE).";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_7")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_8")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test17(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | (vC & vD))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Conjunction(new Proposition("vC"), new Proposition("vD"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_7")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_8")))));
        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test18(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & (vC | vD))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Disjunction(new Proposition("vC"), new Proposition("vD"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vE"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Negation(new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_3")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_9")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Negation(new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_11")))));
        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test19(){
        String data = "fof(ax,axiom, vA <=> ((vB & vC & vD) | (vA & ~ vB & ~ vC) | (vA & ~ vB & vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),
                new Disjunction(new Disjunction(new Conjunction(
                        new Conjunction(new Proposition("vB"),new Proposition("vC")),new Proposition("vD")),
                        new Conjunction(new Conjunction(new Proposition("vA"),new Negation(new Proposition("vB"))),new Negation(new Proposition("vC")))),
                        new Conjunction(new Conjunction(new Proposition("vA"),new Negation(new Proposition("vB"))),new Proposition("vD"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Conjunction(new Proposition("gen_0"),new Proposition("gen_1")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Conjunction(new Proposition("gen_2"),new Proposition("gen_3")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Negation(new Proposition("gen_7")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_8")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Negation(new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_11")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Negation(new Proposition("gen_12")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Conjunction(new Proposition("gen_5"),new Proposition("gen_13")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Negation(new Proposition("gen_16")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_17")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_14"),new Proposition("gen_21")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("gen_22")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test20(){
        String data = "fof(ax,axiom, vB <=> ((~ vA & vB) | (~ vA & vC & ~ vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vB"),
                new Disjunction(
                        new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vC")),new Negation(new Proposition("vD")))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Negation(new Proposition("gen_3")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Negation(new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Conjunction(new Proposition("gen_6"),new Proposition("gen_7")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Negation(new Proposition("gen_9")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_8"),new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Negation(new Proposition("gen_11")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_4"),new Proposition("gen_12")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vB"),new Negation(new Proposition("gen_13")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test21(){
        String data = "fof(ax,axiom, vC <=> ((~ vB & vC & ~ vD) | (~ vA & vB & vD) | (vB & vC & vD) | (vA & ~ vB & ~ vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vC"),
                new Disjunction(new Disjunction(new Disjunction(
                        new Conjunction(new Conjunction(new Negation(new Proposition("vB")),new Proposition("vC")),new Negation(new Proposition("vD"))),
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),new Proposition("vD"))),
                        new Conjunction(new Conjunction(new Proposition("vB"),new Proposition("vC")),new Proposition("vD"))),
                        new Conjunction(new Conjunction(new Proposition("vA"),new Negation(new Proposition("vB"))),new Negation(new Proposition("vD")))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_23"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_24"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_25"),new Negation(new Proposition("gen_24")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_26"),new Conjunction(new Proposition("gen_23"),new Proposition("gen_25")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_27"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_28"),new Negation(new Proposition("gen_27")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_29"),new Conjunction(new Proposition("gen_26"),new Proposition("gen_28")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_30"),new Negation(new Proposition("gen_29")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_31"),new Conjunction(new Proposition("gen_22"),new Proposition("gen_30")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("vC"),new Negation(new Proposition("gen_31")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void test22(){
        String data = "fof(ax,axiom, vD <=> ((~ vA & vB & ~ vC) | (~ vA & vB & vD) | (vA & vC & vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vD"),
                new Disjunction(new Disjunction(
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),new Negation(new Proposition("vC"))),
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),new Proposition("vD"))),
                        new Conjunction(new Conjunction(new Proposition("vA"),new Proposition("vC")),new Proposition("vD"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("vD"),new Negation(new Proposition("gen_22")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }

    @Test
    public void checker(){
        String data = "fof(ax,axiom, vD <=> ((~ vA & vB & ~ vC) | (~ vA & vB & vD) | (vA & vC & vD)))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vD"),
                new Disjunction(new Disjunction(
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),new Negation(new Proposition("vC"))),
                        new Conjunction(new Conjunction(new Negation(new Proposition("vA")),new Proposition("vB")),new Proposition("vD"))),
                        new Conjunction(new Conjunction(new Proposition("vA"), new Proposition("vC")), new Proposition("vD"))))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_0"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_1"),new Negation(new Proposition("gen_0")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_2"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_3"),new Conjunction(new Proposition("gen_1"),new Proposition("gen_2")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_4"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_5"),new Negation(new Proposition("gen_4")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_6"),new Conjunction(new Proposition("gen_3"),new Proposition("gen_5")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_7"),new Negation(new Proposition("gen_6")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_8"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_9"),new Negation(new Proposition("gen_8")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_10"),new Proposition("vB"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_11"),new Conjunction(new Proposition("gen_9"),new Proposition("gen_10")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_12"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_13"),new Conjunction(new Proposition("gen_11"),new Proposition("gen_12")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_14"),new Negation(new Proposition("gen_13")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_15"),new Conjunction(new Proposition("gen_7"),new Proposition("gen_14")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_16"),new Proposition("vA"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_17"),new Proposition("vC"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_18"),new Conjunction(new Proposition("gen_16"),new Proposition("gen_17")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_19"),new Proposition("vD"))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_20"),new Conjunction(new Proposition("gen_18"),new Proposition("gen_19")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_21"),new Negation(new Proposition("gen_20")))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("gen_22"),new Conjunction(new Proposition("gen_15"),new Proposition("gen_21")))));

        expectedL.addRung(new Rung(new Equivalence(new Proposition("vD"),new Negation(new Proposition("gen_22")))));

        DeMorganTransformation dmt = new DeMorganTransformation();
        assertEquals(expectedL, dmt.transform(sourceL));
    }
}
