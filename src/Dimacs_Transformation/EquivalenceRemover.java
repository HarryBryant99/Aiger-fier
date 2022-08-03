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

    public List<Expression> transform(Ladder sourceL){
        ArrayList<Expression> targetList = new ArrayList<>();
        for (Rung r : sourceL.getRungs()) {
            targetList.add(splitEquivalence(r.getEquivalence()));
        }
        return targetList;
    }

    private Expression splitEquivalence(Equivalence equiv){
        List<Expression> splitResult = splitExpression(equiv.getRhsOperand());

        Expression exp = new Disjunction(equiv.getRhsOperand(), new Negation(equiv.getLhsOperand()));

        for (int i = 0; i < splitResult.size(); i++) {
            Expression newExp = new Disjunction(equiv.getLhsOperand(), splitResult.get(i));
            exp = new Conjunction(exp, newExp);
        }

        return exp;
    }

    private List<Expression> splitExpression(Expression exp){
        ArrayList<Expression> expressions = new ArrayList<>();
        if (exp.getClass() == Proposition.class) {
            Negation neg = new Negation(exp);
            expressions.add(neg);
            return expressions;
        } else if (exp.getClass() == Negation.class) {
            //return ((Negation) exp).getOperand();

            expressions.add(((Negation) exp).getOperand());
            return expressions;
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
