package tptp_tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Assert;
import org.junit.Test;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic_parser.Tokeniser;
import prop_logic_parser.TokeniserException;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.Rung;

public class LadderParserTests {

    @Test
    public void canParseLadder() throws IOException {
        String data = "fof(ax,axiom, vA <=> ~(vB | vE)).\n" +
                "fof(ax,axiom, vA <=> vB | vE).";

        Ladder el = new Ladder();
        el.addRung(new Rung(new Equivalence(new Proposition("vA"),new Negation(new Disjunction(new Proposition("vB"),new Proposition("vE"))))));
        el.addRung(new Rung(new Equivalence(new Proposition("vA"),new Disjunction(new Proposition("vB"),new Proposition("vE")))));

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        Ladder l = LadderParser.parseLadder(in);

        assertEquals(el, l);
    }
}
