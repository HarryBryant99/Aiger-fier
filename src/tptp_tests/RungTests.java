package tptp_tests;

import org.junit.Test;
import prop_logic.Equivalence;
import prop_logic.Negation;
import prop_logic.Proposition;
import prop_logic_parser.Tokeniser;
import prop_logic_parser.TokeniserException;
import tptp.Rung;

public class RungTests {

    @Test(expected = IllegalArgumentException.class)
    public void tptpConstructionRejectsNonEquivalance(){
        new Rung(new Proposition("va"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void tptpConstructionRejectsEquivalanceWithoutProposition(){
        new Rung(new Equivalence(new Negation(new Proposition("va")), new Proposition("va")));
    }
}
