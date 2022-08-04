package Dimacs_Transformation_Tests;

import static org.junit.Assert.assertEquals;

import Dimacs_Transformation.CnfConverter;
import Dimacs_Transformation.CnfJavaPrinter;
import java.util.ArrayList;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class CnfConverterTests {

    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, vA <=> ~vB)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Proposition("vB")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Negation(new Proposition("vB"))));
        expectedList.add(new Disjunction(new Proposition("vA"), new Proposition("vB")));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA <=> vBanana_1-)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vBanana_1-"))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vBanana_1-")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vBanana_1-"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vC")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vC")));
        expectedList.add(new Disjunction(new Proposition("vA"),new Disjunction(new Negation(new Proposition("vB")), new Negation(new Proposition("vC")))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test5(){
        String data = "fof(ax,axiom, vA <=> vB | vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vE"))));
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")),new Disjunction(new Proposition("vB"), new Proposition("vE"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE"))))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Proposition("vA"), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Proposition("vE")));
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")),new Disjunction(new Negation(new Proposition("vB")), new Negation(new Proposition("vE")))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> (vB & vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vC))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Proposition("vB"),new Proposition("vC"))))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Negation(new Proposition("vB")),new Proposition("vE"))))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }

    @Test
    public void test16(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE)).\n" +
                "fof(ax,axiom, vA <=> vB & vE).";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Conjunction(new Proposition("vB"),new Proposition("vE"))))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        ArrayList expectedList = new ArrayList();
        expectedList.add(new Disjunction(new Negation(new Proposition("vA")), new Proposition("vB")));
        expectedList.add(new Disjunction(new Proposition("vA"), new Negation(new Proposition("vB"))));

        CnfConverter cc = new CnfConverter();
        assertEquals(expectedList, cc.transform(sourceL));
    }
}
