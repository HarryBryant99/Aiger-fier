package tptp_parser_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import tptp_parser.Tokeniser;
import tptp_parser.TokeniserException;

public class TokeniserTests {

    @Test
    public void canParsePropositions(){
        Tokeniser t = new Tokeniser("  va  vb  vc vd ");
        assertEquals("va", t.peek());
        t.advance();
        assertEquals("vb", t.peek());
        t.advance();
        assertEquals("vc", t.peek());
        t.advance();
        assertEquals("vd", t.peek());
    }

    @Test
    public void canParseTokens(){
        Tokeniser t = new Tokeniser("  va |& <=>&vb~vc ()");
        assertEquals("va", t.peek());
        t.advance();
        assertEquals("|", t.peek());
        t.advance();
        assertEquals("&", t.peek());
        t.advance();
        assertEquals("<=>", t.peek());
        t.advance();
        assertEquals("&", t.peek());
        t.advance();
        assertEquals("vb", t.peek());
        t.advance();
        assertEquals("~", t.peek());
        t.advance();
        assertEquals("vc", t.peek());
        t.advance();
        assertEquals("(", t.peek());
        t.advance();
        assertEquals(")", t.peek());
        t.advance();
    }

    @Test
    public void canReachEnd(){
        Tokeniser t = new Tokeniser("va");
        assertFalse(t.atEnd());
        t.advance();
        assertTrue(t.atEnd());
    }

    @Test(expected = TokeniserException.class)
    public void peekAtEndThrowsException(){
        Tokeniser t = new Tokeniser("va");
        t.advance();
        t.peek();
    }

    @Test(expected = TokeniserException.class)
    public void advanceAtEndThrowsException(){
        Tokeniser t = new Tokeniser("va");
        t.advance();
        t.advance();
    }

    @Test
    public void emptyIsAtEnd(){
        Tokeniser t = new Tokeniser("  ");
        assertTrue(t.atEnd());
    }

}
