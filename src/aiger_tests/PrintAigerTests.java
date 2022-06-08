package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.And;
import aiger.InputPropositions;
import aiger.Latch;
import aiger.Output;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.Rung;
import tseitin_transformation.TseitinTransformation;

public class PrintAigerTests {
    @Test
    public void test1() throws IOException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        File safetyFile = new File("Safety2.tptp");
        in = new FileInputStream(safetyFile);
        Ladder safety = LadderParser.parseLadder(in);

        String data = "fof(ax,axiom, vA <=> vB) - saftey property = vB";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Proposition("vB"))));

        TseitinTransformation tt = new TseitinTransformation();
        AigerTransformation aig = new AigerTransformation(iv.getHashMap());

        Aig sourceAiger = aig.convertLadder(tt.transform(sourceL));

        Ladder transformed = tt.transform(safety);
        sourceAiger.addAllComponents(aig.addSafetyProperty(transformed));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,4,1));
        expectedAig.addComponent(new Output(7));

        assertEquals(expectedAig, sourceAiger);
    }

    @Test
    public void test2() throws IOException {
        File input = new File("input.txt");
        InputStream in = new FileInputStream(input);
        InputPropositions iv = new InputPropositions(in);

        File safetyFile = new File("Safety2.tptp");
        in = new FileInputStream(safetyFile);
        Ladder safety = LadderParser.parseLadder(in);

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

        TseitinTransformation tt = new TseitinTransformation();
        AigerTransformation aig = new AigerTransformation(iv.getHashMap());

        Aig sourceAiger = aig.convertLadder(sourceL);

        Ladder transformed = tt.transform(safety);
        sourceAiger.addAllComponents(aig.addSafetyProperty(transformed));

        // TODO: Calculate real expected result
        Aig expectedAig = new Aig();
        expectedAig.addComponent(new Latch(2,4,1));
        expectedAig.addComponent(new Latch(6,3,0));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,1));
        expectedAig.addComponent(new And(14,6,12));
        expectedAig.addComponent(new Latch(16,18,0));
        expectedAig.addComponent(new Latch(20,17,1));
        expectedAig.addComponent(new And(22,14,20));
        expectedAig.addComponent(new Latch(24,23,1));
        expectedAig.addComponent(new Latch(26,4,1));
        expectedAig.addComponent(new Output(27));

        assertEquals(expectedAig, sourceAiger);
    }
}
