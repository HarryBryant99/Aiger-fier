package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerLadderTransformation;
import aiger.And;
import aiger.InputPropositions;
import aiger.Latch;
import aiger.Output;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Equivalence;
import prop_logic.Proposition;
import safety_condition_transformation.SafetyConditionTransformation;
import tptp.Ladder;
import tptp.Rung;
import tptp.SafetyCondition;
import tseitin_transformation.TseitinTransformation;

public class PrintAigerTests {
    @Test
    public void test1() throws IOException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vB) - saftey property = vB";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Proposition("vB"));

        TseitinTransformation tt = new TseitinTransformation();
        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        AigerLadderTransformation aig = new AigerLadderTransformation(iv.getHashMap());

        Aig newAiger = aig.convertLadder(tt.transform(sourceL));
        Aig scAiger = aig.convertSafetyCondition(sct.transform(sourceSC));

        newAiger.addAllComponents(scAiger.getComponents());

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(4,4,1));
        expectedAig.addComponent(new Output(4));

        assertEquals(expectedAig, newAiger);
    }

    @Test
    public void test2() throws IOException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        String data = "fof(ax,axiom, vA <=> vB & vC)";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Conjunction(new Proposition("vB"),new Proposition("vE")))));

        SafetyCondition sourceSC = new SafetyCondition();
        sourceSC.addExpression(new Proposition("vB"));

        TseitinTransformation tt = new TseitinTransformation();
        SafetyConditionTransformation sct = new SafetyConditionTransformation();
        AigerLadderTransformation aig = new AigerLadderTransformation(iv.getHashMap());

        Aig newAiger = aig.convertLadder(tt.transform(sourceL));
        Aig scAiger = aig.convertSafetyCondition(sct.transform(sourceSC));

        newAiger.addAllComponents(scAiger.getComponents());

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,8,0));
        expectedAig.addComponent(new And(10,2,6));
        expectedAig.addComponent(new Latch(4,4,1));
        expectedAig.addComponent(new Latch(8,8,0));
        expectedAig.addComponent(new Output(4));

        assertEquals(expectedAig, newAiger);
    }
}
