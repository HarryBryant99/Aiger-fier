public class Rung {

    private String primeVar;
    private String operand;

    private Formula formula;

    public Rung(String primeVar, String operand) {
        this.primeVar = primeVar;
        this.operand = operand;

        this.formula = new Formula(operand);

        //System.out.println(formula.listComponents(primeVar));
    }

    public String getPrimeVar() {
        return primeVar;
    }

    public void setPrimeVar(String primeVar) {
        this.primeVar = primeVar;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return (primeVar + " <=> " + formula);
    }

    public String tseitin(){
        return formula.listComponents(primeVar);
    }
}
