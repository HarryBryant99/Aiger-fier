package tseitin_transformation_tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.Rung;
import tseitin_transformation.TseitinTransformation;

public class TseitinTransformationTests {

    @Test
    public void test1(){
        String data = "fof(ax,axiom, vA <=> ~(vB | vE)).\n" +
                "fof(ax,axiom, vA <=> vB | vE).";

        Ladder sourceL = new Ladder();
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));
        sourceL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        // TODO: Calculate real expected result
        Ladder expectedL = new Ladder();
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));
        expectedL.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        TseitinTransformation tt = new TseitinTransformation();
        assertEquals(expectedL, tt.transform(sourceL));
    }
}
