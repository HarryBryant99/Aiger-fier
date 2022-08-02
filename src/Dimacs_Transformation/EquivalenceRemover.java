package Dimacs_Transformation;

import java.util.ArrayList;
import java.util.List;
import prop_logic.Conjunction;
import prop_logic.Disjunction;
import prop_logic.Equivalence;
import prop_logic.Expression;
import prop_logic.Negation;
import prop_logic.Proposition;
import tptp.Ladder;
import tptp.Rung;

public class EquivalenceRemover {
    private static final String GEN_NAME_PREFIX = "gen_";
    private int newVarNextNumber = 0;

    private String genNewName() {
        return GEN_NAME_PREFIX + newVarNextNumber++;
    }

    public Ladder transform(Ladder sourceL){
        Ladder targetL = new Ladder();
        for (Rung r : sourceL.getRungs()) {
            for(Equivalence equ : splitEquivalence(r.getEquivalence())) {
                targetL.addRung(new Rung(equ));
            }
        }
        return targetL;
    }

    private List<Equivalence> splitEquivalence(Equivalence equiv){
        Expression splitResult = splitExpression(equiv.getRhsOperand());

        ArrayList<Equivalence> result = new ArrayList<>();
        //result.addAll(splitResult.equivalences);
        result.add(new Equivalence(equiv.getLhsOperand(), splitResult));

        return result;
    }

    private Expression splitExpression(Expression exp){
        if (exp.getClass() == Proposition.class) {
            Negation neg = new Negation(exp);
            return neg;
        } else if (exp.getClass() == Negation.class) {
            return ((Negation) exp).getOperand();
        } else if (exp.getClass() == Equivalence.class || exp.getClass() == Conjunction.class) {
            throw new IllegalStateException("How the hell did we get this");
        } else if (exp.getClass() == Disjunction.class) {
//            Disjunction dis = (Disjunction) exp;
//
//            Result splitResultLhs = splitExpression(dis.getLhsOperand());
//            String newNameLhs = genNewName();
//            Equivalence equivLhs = new Equivalence(new Proposition(newNameLhs), splitResultLhs.finalExpression);
//
//            Result splitResultRhs = splitExpression(dis.getRhsOperand());
//            String newNameRhs = genNewName();
//            Equivalence equivRhs = new Equivalence(new Proposition(newNameRhs), splitResultRhs.finalExpression);
//
//            ArrayList<Equivalence> resultEquivs = new ArrayList<>();
//            resultEquivs.addAll(splitResultLhs.equivalences);
//            resultEquivs.add(equivLhs);
//            resultEquivs.addAll(splitResultRhs.equivalences);
//            resultEquivs.add(equivRhs);
//
//            return new Result(resultEquivs, new Disjunction(new Proposition(newNameLhs), new Proposition(newNameRhs)));
            return null;
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
