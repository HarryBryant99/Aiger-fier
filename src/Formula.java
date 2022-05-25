public class Formula {

    private boolean isLeftNegative;
    private boolean isRightNegative;
    private char operator;
    private Formula leftFormula;
    private Formula rightFormula;

    private String leftString;
    private String rightString;

    private int depth = 0;

    public Formula(String formula) {
        processFormula(formula);
    }

    public Formula() {

    }

    @Override
    public String toString() {
        String output = "";
        if (getLeftString() == null){
            output = "(" + getLeftFormula().toString() + ")";
        } else {
            if (isLeftNegative){
                output = "~ ";
            }
            output += getLeftString();
        }

        if (getOperator() == '&' || getOperator() == '|') {
            output += " " + getOperator() + " ";
        }

        if (getRightString() == null && getRightFormula() != null){
            output += "(" + getRightFormula().toString() + ")";
        } else if (getRightFormula() != null){
            if (isRightNegative){
                output = "~ ";
            }
            output += getRightString();
        }

        return output;
    }

    private void processFormula(String formula){
        int pointer = 0;
        int depth = 0;
        boolean isRightFormula = false;

        for (int i = pointer; i < formula.length(); i++) {
            //System.out.println(formula.charAt(i));
            if (formula.charAt(i) == '~'){
                negative(isRightFormula);
            } else if (formula.charAt(i) == '('){
                pointer = findBracket(formula, i);
                //Recurse on formula
                if (!isRightFormula) {
                    leftFormula = new Formula(formula.substring(i+1, pointer));
                    isRightFormula = true;
                } else {
                    rightFormula = new Formula(formula.substring(i+1, pointer));
                }
                i = pointer;

            } else if (formula.charAt(i) == '&'){
                setOperator('&');
            } else if (formula.charAt(i) == '|'){
                setOperator('|');
            } else if (formula.charAt(i) == 'v'){
                pointer = variableFound(formula, i);
                if (!isRightFormula) {
                    leftFormula = new Variable(formula.substring(i, pointer));
                    setLeftString(formula.substring(i, pointer));
                    isRightFormula = true;
                } else {
                    rightFormula = new Variable(formula.substring(i, pointer));
                    setRightString(formula.substring(i, pointer));
                }
                i = pointer;
            }
        }
    }

    public int variableFound(String formula, int starting){
        int position = formula.length();
        for (int i = starting; i < formula.length(); i++) {
            if (formula.charAt(i) == ' '){
                    position = i;
                    break;
            }
        }

        return position;
    }

    public void negative(boolean isRightFormula){
        if (isRightFormula){
            setRightNegative(true);
        } else {
            setLeftNegative(true);
        }
    }

    public int findBracket(String formula, int starting){
        int position = 0;
        int bracketsFound = 0;

        for (int i = starting; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') {
                setDepth(getDepth()+1);

            } else if (formula.charAt(i) == ')'){
                bracketsFound++;

                if (bracketsFound == getDepth()) {
                    position = i;
                    break;
                }
            }
        }
        setDepth(0);
        return position;
    }

    public boolean isLeftNegative() {
        return isLeftNegative;
    }

    public void setLeftNegative(boolean leftNegative) {
        isLeftNegative = leftNegative;
    }

    public boolean isRightNegative() {
        return isRightNegative;
    }

    public void setRightNegative(boolean rightNegative) {
        isRightNegative = rightNegative;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public Formula getLeftFormula() {
        return leftFormula;
    }

    public void setLeftFormula(Formula leftFormula) {
        this.leftFormula = leftFormula;
    }

    public Formula getRightFormula() {
        return rightFormula;
    }

    public void setRightFormula(Formula rightFormula) {
        this.rightFormula = rightFormula;
    }

    public String getLeftString() {
        return leftString;
    }

    public void setLeftString(String leftString) {
        this.leftString = leftString;
    }

    public String getRightString() {
        return rightString;
    }

    public void setRightString(String rightString) {
        this.rightString = rightString;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
