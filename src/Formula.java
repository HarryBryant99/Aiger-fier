public class Formula {

//    private boolean isLeftNegative;
//    private boolean isRightNegative;
//    private char operator;
//    private Formula leftFormula;
//    private Formula rightFormula;
//
//    private String leftString;
//    private String rightString;
//
//    private boolean andfied;
//
//    private int depth = 0;
//
//    public Formula(String formula) {
//        processFormula(formula);
//    }
//
//    public Formula() {
//
//    }
//
//    @Override
//    public String toString() {
//        String output = "";
//        if (getLeftString() == null){
//            if (isLeftNegative){
//                output += "~ ";
//            }
//            output += "(" + getLeftFormula().toString() + ")";
//        } else {
//            if (isLeftNegative){
//                output += "~ ";
//            }
//            output += getLeftString();
//        }
//
//        if (getOperator() == '&' || getOperator() == '|') {
//            output += " " + getOperator() + " ";
//        }
//
//        if (getRightString() == null && getRightFormula() != null){
//            if (isRightNegative){
//                output += " ~ ";
//            }
//            output += "(" + getRightFormula().toString() + ")";
//        } else if (getRightFormula() != null){
//            if (isRightNegative){
//                output += " ~ ";
//            }
//            output += getRightString();
//        }
//
//        return output;
//    }
//
//    private void processFormula(String formula){
//        int pointer = 0;
//        int depth = 0;
//        boolean isRightFormula = false;
//
//        for (int i = pointer; i < formula.length(); i++) {
//            //System.out.println(formula.charAt(i));
//            if (formula.charAt(i) == '~'){
//                negative(isRightFormula);
//            } else if (formula.charAt(i) == '('){
//                pointer = findBracket(formula, i);
//                //Recurse on formula
//                if (!isRightFormula) {
//                    leftFormula = new Formula(formula.substring(i+1, pointer));
//                    isRightFormula = true;
//                } else {
//                    rightFormula = new Formula(formula.substring(i+1, pointer));
//                }
//                i = pointer;
//
//            } else if (formula.charAt(i) == '&'){
//                setOperator('&');
//            } else if (formula.charAt(i) == '|'){
//                setOperator('|');
//            } else if (formula.charAt(i) == 'v'){
//                pointer = variableFound(formula, i);
//                if (!isRightFormula) {
//                    leftFormula = new Variable(formula.substring(i, pointer));
//                    setLeftString(formula.substring(i, pointer));
//                    isRightFormula = true;
//                } else {
//                    rightFormula = new Variable(formula.substring(i, pointer));
//                    setRightString(formula.substring(i, pointer));
//                }
//                i = pointer;
//            }
//        }
//        if (getOperator() == '|'){
//            andify();
//        }
//
////        System.out.println(getLeftFormula().isAndfied());
////        System.out.println(isLeftNegative());
//
//        if (isLeftNegative && getLeftFormula().getLeftString() == null && getLeftFormula().isLeftNegative){
//            //System.out.println("Double negation");
//            setLeftNegative(false);
//            getLeftFormula().setLeftNegative(false);
//            removeBrackets();
//        }
//
//        if (isRightNegative && getRightFormula().getLeftString() == null && getRightFormula().isLeftNegative){
//            setRightNegative(false);
//            getRightFormula().setLeftNegative(false);
//        }
//
////        if (getLeftFormula().isAndfied() && isLeftNegative()){
////            setLeftNegative(false);
////            getLeftFormula().setLeftNegative(false);
////            System.out.println("andfied2");
////        }
////
////        if (getRightFormula() != null && getRightFormula().isAndfied() && isRightNegative()){
////            setRightNegative(false);
////            getRightFormula().setRightNegative(false);
////        }
//    }
//
//    private void andify(){
//        String and = null;
////        if (getLeftString() != null) {
////            and = "(~ " + getLeftString() + " & ~ " + getRightString() + ")";
////        } else {
////            and = "(~ " + getLeftFormula().toString() + " & ~ " + getRightString() + ")";
////        }
//
//        and = "(";
//        if (getLeftString() == null){
//            if (!isLeftNegative){
//                and += "~ ";
//            }
//            and += "(" + getLeftFormula().toString() + ")";
//        } else {
//            if (!isLeftNegative){
//                and += "~ ";
//            }
//            and += getLeftString();
//        }
//
//        and += " &";
//
//        if (getRightString() == null && getRightFormula() != null){
//            if (!isRightNegative){
//                and += " ~ ";
//            }
//            and += "(" + getRightFormula().toString() + ")";
//        } else if (getRightFormula() != null){
//            if (!isRightNegative){
//                and += " ~ ";
//            }
//            and += getRightString();
//        }
//        and += ")";
//
//        setAndfied(true);
//
//        Formula newFormula = new Formula(and);
//        setLeftNegative(true);
//        setLeftFormula(newFormula);
//        setLeftString(null);
//
//        setRightFormula(null);
//        setRightString("");
//        setOperator(' ');
//
//        //System.out.println(getLeftString());
//        //System.out.println(toString());
//    }
//
//    private void removeBrackets(){
//        //System.out.println(getLeftFormula().toString());
//        if (getLeftFormula().toString().charAt(0) == '(' &&
//                getLeftFormula().toString().charAt(0) == '('){
//           //System.out.println(getLeftFormula().getLeftString());
//        }
//    }
//
//    private int variableFound(String formula, int starting){
//        int position = formula.length();
//        for (int i = starting; i < formula.length(); i++) {
//            if (formula.charAt(i) == ' '){
//                    position = i;
//                    break;
//            }
//        }
//
//        return position;
//    }
//
//    private void negative(boolean isRightFormula){
//        if (isRightFormula){
//            setRightNegative(true);
//        } else {
//            if (!isLeftNegative) {
//                setLeftNegative(true);
//            } else {
//                setLeftNegative(false);
//            }
//        }
//    }
//
//    public int findBracket(String formula, int starting){
//        int position = 0;
//        int bracketsFound = 0;
//
//        for (int i = starting; i < formula.length(); i++) {
//            if (formula.charAt(i) == '(') {
//                setDepth(getDepth()+1);
//
//            } else if (formula.charAt(i) == ')'){
//                bracketsFound++;
//
//                if (bracketsFound == getDepth()) {
//                    position = i;
//                    break;
//                }
//            }
//        }
//        setDepth(0);
//        return position;
//    }
//
//    public boolean isLeftNegative() {
//        return isLeftNegative;
//    }
//
//    public void setLeftNegative(boolean leftNegative) {
//        isLeftNegative = leftNegative;
//    }
//
//    public boolean isRightNegative() {
//        return isRightNegative;
//    }
//
//    public void setRightNegative(boolean rightNegative) {
//        isRightNegative = rightNegative;
//    }
//
//    public char getOperator() {
//        return operator;
//    }
//
//    public void setOperator(char operator) {
//        this.operator = operator;
//    }
//
//    public Formula getLeftFormula() {
//        return leftFormula;
//    }
//
//    public void setLeftFormula(Formula leftFormula) {
//        this.leftFormula = leftFormula;
//    }
//
//    public Formula getRightFormula() {
//        return rightFormula;
//    }
//
//    public void setRightFormula(Formula rightFormula) {
//        this.rightFormula = rightFormula;
//    }
//
//    public String getLeftString() {
//        return leftString;
//    }
//
//    public void setLeftString(String leftString) {
//        this.leftString = leftString;
//    }
//
//    public String getRightString() {
//        return rightString;
//    }
//
//    public void setRightString(String rightString) {
//        this.rightString = rightString;
//    }
//
//    public int getDepth() {
//        return depth;
//    }
//
//    public void setDepth(int depth) {
//        this.depth = depth;
//    }
//
//    public boolean isAndfied() {
//        return andfied;
//    }
//
//    public void setAndfied(boolean andfied) {
//        this.andfied = andfied;
//    }
//
//    public String listComponents(String latchName){
//        String output = latchName + " <=> ";
//        if (getLeftString() == null){
//            if (isLeftNegative){
//                output += "~ ";
//            }
//            //output += "(" + getLeftFormula().listComponents(latchName) + ")";
//            output += "(" + latchName+1 + ")\n";
//            output += getLeftFormula().listComponents(latchName + 1);
//        } else {
//            if (isLeftNegative){
//                output += "~ ";
//            }
//            output += getLeftString();
//        }
//
//        if (getOperator() == '&' || getOperator() == '|') {
//            output += " " + getOperator() + " ";
//        }
//
//        if (getRightString() == null && getRightFormula() != null){
//            if (isRightNegative){
//                output += "~ ";
//            }
//            output += "(" + latchName+2 + ")\n";
//            output += getRightFormula().listComponents(latchName + 2);
//        } else if (getRightFormula() != null){
//            if (isRightNegative){
//                output += "~ ";
//            }
//            output += getRightString();
//        }
//
//        return output;
//    }
}