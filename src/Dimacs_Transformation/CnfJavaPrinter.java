package Dimacs_Transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class CnfJavaPrinter {
    public String transform(Ladder sourceL){
        String targetList = new String();
        int counter = 0;
        for (Rung r : sourceL.getRungs()) {
            if (counter < sourceL.getRungs().size()-1) {
                targetList += (splitEquivalence(r.getEquivalence())) + " and ";
            } else {
                targetList += (splitEquivalence(r.getEquivalence()));
            }
            counter++;
        }
        return targetList;
    }

    private String splitEquivalence(Equivalence equiv){
        String splitResult = splitExpression(equiv.getRhsOperand());
        return "(" + equiv.getLhsOperand() + " iff (" + splitResult + "))";
    }

    private String splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            return ((Proposition) exp).getName();
        } else if (exp.getClass() == Negation.class) {
            if (((Negation) exp).getOperand().getClass() == Proposition.class) {
                return "neg " + ((Proposition) ((Negation) exp).getOperand()).getName();
            } else {
                return "neg (" + splitExpression(((Negation) exp).getOperand()) + ")";
            }
        } else if (exp.getClass() == Equivalence.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Conjunction.class){
            Conjunction con = (Conjunction) exp;

            String conjunction = new String();
            if ((con.getLhsOperand().getClass() == Disjunction.class) || (con.getLhsOperand().getClass() == Conjunction.class)) {
                conjunction += "(" + splitExpression(con.getLhsOperand()) + ")";
            } else {
                conjunction += "" + splitExpression(con.getLhsOperand());
            }

            if ((con.getRhsOperand().getClass() == Disjunction.class) || (con.getRhsOperand().getClass() == Conjunction.class)) {
                conjunction += " and (" + splitExpression(con.getRhsOperand()) + ")";
            } else {
                conjunction += " and " + splitExpression(con.getRhsOperand()) + "";
            }

            return conjunction;
        } else if (exp.getClass() == Disjunction.class) {
            Disjunction dis = (Disjunction) exp;

            String disjunction = new String();
            if ((dis.getLhsOperand().getClass() == Disjunction.class) || (dis.getLhsOperand().getClass() == Conjunction.class)) {
                disjunction += "(" + splitExpression(dis.getLhsOperand()) + ")";
            } else{
                disjunction += "" + splitExpression(dis.getLhsOperand());
            }

            if ((dis.getRhsOperand().getClass() == Disjunction.class) || (dis.getRhsOperand().getClass() == Conjunction.class)) {
                disjunction += " or (" + splitExpression(dis.getRhsOperand()) + ")";
            } else {
                disjunction += " or " + splitExpression(dis.getRhsOperand()) + "";
            }

            return disjunction;
        } else {
            throw new IllegalStateException("What is thi sub type?");
        }
    }

    private class Result {
        public List<Equivalence> equivalences;
        public Expression finalExpression;

        public Result(List<Equivalence> equivalences, Expression finalExpression) {
            this.equivalences = equivalences;
            this.finalExpression = finalExpression;
        }

        public Result(Expression finalExpression) {
            this.equivalences = new ArrayList<>();
            this.finalExpression = finalExpression;
        }
    }
}
