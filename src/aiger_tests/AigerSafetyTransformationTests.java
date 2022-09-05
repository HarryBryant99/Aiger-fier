package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerLadderTransformation;
import aiger.And;
import aiger.Output;
import org.junit.Test;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.DeMorganConjunction;
import tptp.SafetyCondition;

public class AigerSafetyTransformationTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Proposition("vB"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, ~vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Proposition("vB")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA & vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vA"),new Proposition("vB"),new Proposition("sc_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vB & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vC"), new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test5() {
        String data = "fof(ax,axiom, vB | vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")), new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, ~vB | ~vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test8(){
        String data = "fof(ax,axiom, ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, ~(vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, ~(~vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,7));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, (vB | vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("sc_0")),new Proposition("vC"),new Proposition("sc_1")));
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_1"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,6));
        expectedAig.addComponent(new And(4,9,11));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("vC")),new Proposition("sc_1")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_1")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new And(4,8,10));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_0"),new Negation(new Proposition("vC")),new Proposition("sc_1")));
        sourceSC.addExpression(new DeMorganConjunction(new Negation (new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        sourceSC.addExpression(new Negation(new Proposition("sc_1")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,7));
        expectedAig.addComponent(new And(4,9,11));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_0"),new Proposition("vC"),new Proposition("sc_1")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_1"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new And(4,8,10));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> ~(vB | ~vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("vB")),new Proposition("vE"),new Proposition("sc_0")));
        sourceSC.addExpression(new Proposition("sc_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,6));
        expectedAig.addComponent(new Output(2));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void testE3(){
        String data = "fof(ax,axiom, ((vC & ~ vD) | (vB & ~ vD) | (vB & vC) | (vA & ~ vB) | (~ vA & ~ vC & vD)))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_6"), new Negation(new Proposition("sc_8")),new Proposition("sc_9")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vC"),new Negation(new Proposition("vD")),new Proposition("sc_0")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"),new Negation(new Proposition("vD")),new Proposition("sc_1")));
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("sc_1")),new Proposition("sc_2")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vB"), new Proposition("vC"),new Proposition("sc_3")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_2"),new Negation(new Proposition("sc_3")),new Proposition("sc_4")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("vA"),new Negation(new Proposition("vB")),new Proposition("sc_5")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_4"),new Negation(new Proposition("sc_5")),new Proposition("sc_6")));
        sourceSC.addExpression(new DeMorganConjunction(new Negation(new Proposition("vA")),new Negation(new Proposition("vC")),new Proposition("sc_7")));
        sourceSC.addExpression(new DeMorganConjunction(new Proposition("sc_7"),new Proposition("vD"),new Proposition("sc_8")));
        sourceSC.addExpression(new Negation(new Proposition("sc_9")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,7));
        expectedAig.addComponent(new And(8,10,13));
        expectedAig.addComponent(new And(14,16,13));
        expectedAig.addComponent(new And(18,9,15));
        expectedAig.addComponent(new And(20,16,10));
        expectedAig.addComponent(new And(22,18,21));
        expectedAig.addComponent(new And(24,26,17));
        expectedAig.addComponent(new And(4,22,25));
        expectedAig.addComponent(new And(28,27,11));
        expectedAig.addComponent(new And(6,28,12));
        expectedAig.addComponent(new Output(3));

        AigerLadderTransformation tt = new AigerLadderTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }
}
