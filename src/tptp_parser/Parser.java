package tptp_parser;

import com.sun.org.apache.xpath.internal.operations.And;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;

public class Parser {
    public static Expression parse(String data){
        Tokeniser t = new Tokeniser(data);
        return parseExpression(t);
    }

    private static Expression parseExpression(Tokeniser t){
        return parseEquivalence(t);
    }

    private static Expression parseEquivalence(Tokeniser t){
        Expression lhs = parseDisjunction(t);

        while (t.isMatchAndAdvance("<=>")){
            Expression rhs = parseDisjunction(t);
            lhs = new Equivalence(lhs, rhs);
        }
        return lhs;
    }

    private static Expression parseDisjunction(Tokeniser t){
        Expression lhs = parseConjunction(t);

        while (t.isMatchAndAdvance("|")){
            Expression rhs = parseConjunction(t);
            lhs = new Disjunction(lhs, rhs);
        }
        return lhs;
    }

    private static Expression parseConjunction(Tokeniser t){
        Expression lhs = parseNegation(t);

        while (t.isMatchAndAdvance("&")){
            Expression rhs = parseNegation(t);
            lhs = new Conjunction(lhs, rhs);
        }
        return lhs;
    }

    private static Expression parseNegation(Tokeniser t){
        if (t.isMatchAndAdvance("~")){
            Expression rhs = parseNegation(t);
            return new Negation(rhs);
        }
        return parsePrimary(t);
    }

    private static Expression parsePrimary(Tokeniser t){
        String variable = t.peekVariableAndAdvance();
        if (variable != null){
            return new Proposition(variable);
        }

        if (t.isMatchAndAdvance("(")){
            Expression exp = parseExpression(t);
            if (t.isMatchAndAdvance(")")){
                return exp;
            }

            throw new ParserException(t.getPosition() + 1,"Expecting ')' after expression");
        }
        throw new ParserException(t.getPosition() + 1,"Unexpected symbol " + t.peek().charAt(0));
    }

}
