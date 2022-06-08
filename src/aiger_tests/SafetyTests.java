package aiger_tests;

import static org.junit.Assert.assertEquals;

import aiger.Aig;
import aiger.AigerTransformation;
import aiger.And;
import aiger.Latch;
import aiger.Output;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import tptp.Ladder;
import tptp.LadderParser;
import tseitin_transformation.TseitinTransformation;

public class SafetyTests {

    @Test
    public void test1() throws IOException {
        File safetyFile = new File("Safety.tptp");
        InputStream in = new FileInputStream(safetyFile);
        Ladder safety = LadderParser.parseLadder(in);

        TseitinTransformation tt = new TseitinTransformation();
        AigerTransformation aig = new AigerTransformation(null);

        Aig newAiger = new Aig();

        Ladder transformed = tt.transform(safety);
        newAiger.addAllComponents(aig.addSafetyProperty(transformed));

        Aig expectedAig = new Aig();

        //TODO add expected
        expectedAig.addComponent(new Latch(2,4,0));
        expectedAig.addComponent(new Latch(6,3,1));
        expectedAig.addComponent(new Latch(8,10,0));
        expectedAig.addComponent(new Latch(12,9,1));
        expectedAig.addComponent(new And(14, 6, 12));
        expectedAig.addComponent(new Latch(16, 15,1));
        expectedAig.addComponent(new Output(17));

        assertEquals(expectedAig, newAiger);
    }
}
