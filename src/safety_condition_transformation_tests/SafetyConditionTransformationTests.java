package safety_condition_transformation_tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic.SafetyConjunction;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.SafetyCondition;
import tptp.SafetyConditionParser;

public class SafetyConditionTransformationTests {
    @Test
    public void test1(){
        String data = "fof(ax,axiom, vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Proposition("vB"));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new Proposition("vB"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test2(){
        String data = "fof(ax,axiom, ~vB)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Proposition("vB")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new Negation(new Proposition("vB")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test3(){
        String data = "fof(ax,axiom, vBanana_1-)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Proposition("vBanana_1-"));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new Proposition("vBanana_1-"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test4(){
        String data = "fof(ax,axiom, vB & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Conjunction(new Proposition("vB"),new Proposition("vC")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vC"), new Proposition("sc_0")));
        expectedSC.addExpression(new Proposition("sc_0"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test5() {
        String data = "fof(ax,axiom, vB | vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Proposition("vB"), new Proposition("vE")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")), new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_0")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test6(){
        String data = "fof(ax,axiom, vA <=> ~vB | ~vE)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_0")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test7(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Conjunction(new Proposition("vB"),new Proposition("vE"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_0")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test8(){
        String data = "fof(ax,axiom, vA <=> ~(vB & vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Conjunction(new Proposition("vB"),new Proposition("vE"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"), new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_0")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test9(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        expectedSC.addExpression(new Proposition("sc_0"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test10(){
        String data = "fof(ax,axiom, vA <=> ~(~vB | vE))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Negation(new Disjunction(new Negation(new Proposition("vB")),new Proposition("vE"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        expectedSC.addExpression(new Proposition("sc_0"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test11(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Conjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("sc_0")),new Proposition("vC"),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        expectedSC.addExpression(new Proposition("sc_1"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test12(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("vC")),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_1")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test13(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) | vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_0"),new Negation(new Proposition("vC")),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Negation (new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        expectedSC.addExpression(new Negation(new Proposition("sc_1")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test14(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) & vC)";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Conjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Proposition("vC")));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_0"),new Proposition("vC"),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("sc_0")));
        expectedSC.addExpression(new Proposition("sc_1"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test15(){
        String data = "fof(ax,axiom, vA <=> (vB | vE) & (vC | vD))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Conjunction(new Disjunction(new Proposition("vB"),new Proposition("vE")),new Disjunction(new Proposition("vC"), new Proposition("vD"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("sc_1")),new Proposition("sc_2")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vB")),new Negation(new Proposition("vE")),new Proposition("sc_0")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vC")),new Negation(new Proposition("vD")),new Proposition("sc_1")));
        expectedSC.addExpression(new Proposition("sc_2"));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test16(){
        String data = "fof(ax,axiom, vA <=> (vB & vE) | (vC & vD))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Conjunction(new Proposition("vB"),new Proposition("vE")),new Conjunction(new Proposition("vC"), new Proposition("vD"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("sc_1")),new Proposition("sc_2")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Proposition("vE"),new Proposition("sc_0")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vC"),new Proposition("vD"),new Proposition("sc_1")));
        expectedSC.addExpression(new Negation(new Proposition("sc_2")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test17(){
        String data = "fof(ax,axiom, (vc | (vb & ~ vd) | (va & ~ vd) | (~ va & ~ vb & vd)))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Disjunction(new Disjunction(new Proposition("vC"),
                new Conjunction(new Proposition("vB"),new Negation(new Proposition("vD")))),
                new Conjunction(new Proposition("vA"),new Negation(new Proposition("vD")))),
                new Conjunction(new Conjunction(new Negation(new Proposition("vA")), new Negation(new Proposition("vB"))), new Proposition("vD"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_3"), new Negation(new Proposition("sc_5")),new Proposition("sc_6")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Negation(new Proposition("vD")),new Proposition("sc_0")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vC")),new Negation(new Proposition("sc_0")),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vA"),new Negation(new Proposition("vD")),new Proposition("sc_2")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_1"), new Negation(new Proposition("sc_2")),new Proposition("sc_3")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vA")),new Negation(new Proposition("vB")),new Proposition("sc_4")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_4"), new Proposition("vD"),new Proposition("sc_5")));
        expectedSC.addExpression(new Negation(new Proposition("sc_6")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }

    @Test
    public void test18(){
        String data = "fof(ax,axiom, ((vC & ~ vD) | (vB & ~ vD) | (vB & vC) | (vA & ~ vB) | (~ vA & ~ vC & vD)))";

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Disjunction(new Disjunction(new Disjunction(new Disjunction(
                new Conjunction(new Proposition("vC"),new Negation(new Proposition("vD"))),
                new Conjunction(new Proposition("vB"),new Negation(new Proposition("vD")))),
                new Conjunction(new Proposition("vB"),new Proposition("vC"))),
                new Conjunction(new Proposition("vA"),new Negation(new Proposition("vB")))),
                new Conjunction(new Conjunction(new Negation(new Proposition("vA")), new Negation(new Proposition("vC"))), new Proposition("vD"))));

        // TODO: Calculate real expected result
        SafetyCondition expectedSC = new SafetyCondition();
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_6"), new Negation(new Proposition("sc_8")),new Proposition("sc_9")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vC"),new Negation(new Proposition("vD")),new Proposition("sc_0")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"),new Negation(new Proposition("vD")),new Proposition("sc_1")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("sc_0")),new Negation(new Proposition("sc_1")),new Proposition("sc_2")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vB"), new Proposition("vC"),new Proposition("sc_3")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_2"),new Negation(new Proposition("sc_3")),new Proposition("sc_4")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("vA"),new Negation(new Proposition("vB")),new Proposition("sc_5")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_4"),new Negation(new Proposition("sc_5")),new Proposition("sc_6")));
        expectedSC.addExpression(new SafetyConjunction(new Negation(new Proposition("vA")),new Negation(new Proposition("vC")),new Proposition("sc_7")));
        expectedSC.addExpression(new SafetyConjunction(new Proposition("sc_7"),new Proposition("vD"),new Proposition("sc_8")));
        expectedSC.addExpression(new Negation(new Proposition("sc_9")));

        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        assertEquals(expectedSC, sct.transform(sourceSC));
    }
}
