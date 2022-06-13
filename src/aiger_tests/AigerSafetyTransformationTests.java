package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.And;
import aiger.Latch;
import aiger.Output;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.SafefyConjunction;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.Rung;
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

        AigerTransformation tt = new AigerTransformation(null);
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

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vA & vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vA"),new Proposition("vB"),new Proposition("gen_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vB & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vC"), new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test5() {
        String data = "fof(ax,axiom, vB | vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")), new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test8(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_0")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,7));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("gen_0")),new Proposition("vC"),new Proposition("gen_1")));
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_1"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,6));
        expectedAig.addComponent(new And(4,9,11));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("gen_0")),new Negation(new Proposition("vC")),new Proposition("gen_1")));
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_1")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,7));
        expectedAig.addComponent(new And(4,8,10));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("gen_0"),new Negation(new Proposition("vC")),new Proposition("gen_1")));
        sourceSC.addExpression(new SafefyConjunction(new Negation (new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("gen_0")));
        sourceSC.addExpression(new Negation(new Proposition("gen_1")));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,7));
        expectedAig.addComponent(new And(4,9,11));
        expectedAig.addComponent(new Output(3));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Proposition("gen_0"),new Proposition("vC"),new Proposition("gen_1")));
        sourceSC.addExpression(new SafefyConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_1"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,4,6));
        expectedAig.addComponent(new And(4,8,10));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> ~(vB | ~vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new SafefyConjunction(new Negation(new Proposition("vB")),new Proposition("vE"),new Proposition("gen_0")));
        sourceSC.addExpression(new Proposition("gen_0"));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new And(2,5,6));
        expectedAig.addComponent(new Output(2));

        AigerTransformation tt = new AigerTransformation(null);
        assertEquals(expectedAig, tt.convertSafetyCondition(sourceSC));
    }
}
