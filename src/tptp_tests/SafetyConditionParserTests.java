package tptp_tests;

import static org.junit.Assert.assertEquals;

import com.sun.org.apache.xpath.internal.operations.And;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.LadderParser;
import tptp.Rung;
import tptp.SafetyCondition;
import tptp.SafetyConditionParser;

public class SafetyConditionParserTests {

    @Test
    public void canParseSafetyCondition() throws IOException {
        String data = "fof(ax,axiom, vB).";

        SafetyCondition esc = new SafetyCondition();
        esc.addExpression(new Negation(new Proposition("vB")));

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        assertEquals(esc, sc);
    }

    @Test
    public void canParseNegatedSafetyCondition() throws IOException {
        String data = "fof(ax,axiom, ~vB).";

        SafetyCondition esc = new SafetyCondition();
        esc.addExpression(new Negation(new Negation(new Proposition("vB"))));

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        assertEquals(esc, sc);
    }

    @Test
    public void canParseDisjunctionSafetyCondition() throws IOException {
        String data = "fof(ax,axiom, vB | vC).";

        SafetyCondition esc = new SafetyCondition();
        esc.addExpression(new Negation(new Disjunction(new Proposition("vB"), new Proposition("vC"))));

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        assertEquals(esc, sc);
    }

    @Test
    public void canParseConjunctionSafetyCondition() throws IOException {
        String data = "fof(ax,axiom, vB & vC).";

        SafetyCondition esc = new SafetyCondition();
        esc.addExpression(new Negation(new Conjunction(new Proposition("vB"), new Proposition("vC"))));

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        SafetyCondition sc = SafetyConditionParser.parseSafetyCondition(in);

        assertEquals(esc, sc);
    }
}
