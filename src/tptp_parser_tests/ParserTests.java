package tptp_parser_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp_parser.Parser;
import tptp_parser.Tokeniser;

public class ParserTests {
    @Test
    public void canParseProposition(){
        Expression e = Parser.parse("va");
        Expression expected = new Proposition("va");
        assertEquals(expected, e);
    }

    @Test
    public void canParseNegation(){
        Expression e = Parser.parse("~va");
        Expression expected = new Negation(new Proposition("va"));
        assertEquals(expected, e);
    }

    @Test
    public void canParseConjunction(){
        Expression e = Parser.parse("va &vb");
        Expression expected = new Conjunction(new Proposition("va"), new Proposition("vb"));
        assertEquals(expected, e);
    }

    @Test
    public void canParseDisjunction(){
        Expression e = Parser.parse("va |vb");
        Expression expected = new Disjunction(new Proposition("va"), new Proposition("vb"));
        assertEquals(expected, e);
    }

    @Test
    public void canParseEquivalance(){
        Expression e = Parser.parse("va <=>vb");
        Expression expected = new Equivalence(new Proposition("va"), new Proposition("vb"));
        assertEquals(expected, e);
    }

    @Test
    public void canParseParenthesis(){
        Expression e = Parser.parse("(va)");
        Expression expected = new Proposition("va");
        assertEquals(expected, e);
    }

    @Test
    public void canParseComplexExpression(){
        Expression e = Parser.parse("(va <=> ~(vb |~ vc) & (~~vd | (va & ~vb)))");
        Expression expected = new Equivalence(new Proposition("va"), new Conjunction(new Negation(new Disjunction(new Proposition("vb"), new Negation(new Proposition("vc")))),new Disjunction(new Negation(new Negation(new Proposition("vd"))),new Conjunction(new Proposition("va"),new Negation(new Proposition("vb"))))));
        assertEquals(expected, e);
    }

    @Test
    public void canParseDoubleNegation(){
        Expression e = Parser.parse("~~va");
        Expression expected = new Negation(new Negation(new Proposition("va")));
        assertEquals(expected, e);
    }

    @Test
    public void canParseAssociativeness(){
        Expression e = Parser.parse("va & vb & vc");
        Expression expected = new Conjunction(new Conjunction(new Proposition("va"), new Proposition("vb")), new Proposition("vc"));
        assertEquals(expected, e);
    }


}
