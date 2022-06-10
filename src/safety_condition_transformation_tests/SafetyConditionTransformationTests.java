package safety_condition_transformation_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import prop_logic.Equivalence;
import prop_logic.Proposition;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;
import tseitin_transformation.TseitinTransformation;

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
}
