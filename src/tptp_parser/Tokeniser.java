package tptp_parser;

public class Tokeniser {
    private final String data;
    private int currentStart;

    public Tokeniser(String data) {
        this.data = data;
        currentStart = 0;
    }

    private void consumeWhiteSpace(){
        while (currentStart < data.length()){
            if (Character.isWhitespace(data.charAt(currentStart))){
                currentStart++;
            } else {
                return;
            }
        }
    }

    public String peek() throws TokeniserException {
        consumeWhiteSpace();

        if (atEnd()){
            throw new TokeniserException(currentStart, "Expecting token, but found end of string");
        }

        char c = data.charAt(currentStart);

        //Check for single character symbol
        if (c == '(' || c == ')' || c == '(' || c == '&' || c == '|' || c == '~'){
            return Character.toString(c);
        }

        //Check for multi-char symbols
        if (c == '<'){
            if (data.substring(currentStart, currentStart + 3).equals("<=>")){
                return data.substring(currentStart, currentStart + 3);
            } else {
                throw new TokeniserException(currentStart, "Expecting =>");
            }
        }

        //Try a variable
        if (c == 'v'){
            //Read until end of variable
            int len = 1;
            while (currentStart + len < data.length()){
                char c2 = data.charAt(currentStart + len);

                //Check if ending symbol
                if (!isCharAllowedInVariable(c2)){
                    if (len == 1){
                        throw new TokeniserException(currentStart, "Variable must contain a name");
                    } else {
                        return data.substring(currentStart, currentStart + len);
                    }
                } else if (isCharAllowedInVariable(c2)) {
                    len++;
                } else {
                    throw new TokeniserException(currentStart + len + 1, "Variable cannot contain this '" + c2 + "'");
                }
            }

            //Reached end of string while parsing variable
            if (len == 1){
                throw new TokeniserException(currentStart, "Variable must contain a name");
            } else {
                return data.substring(currentStart, currentStart + len);
            }
        }

        throw new TokeniserException(currentStart, "Unexpected character '" + c + "'");
    }

    private static boolean isCharAllowedInVariable(Character c){
        return Character.isLetterOrDigit(c);
    }

    public boolean atEnd(){
        consumeWhiteSpace();
        return currentStart == data.length();
    }

    public void advance(){
        String token = peek();
        currentStart += token.length();
    }

    /**
     * Test if the next token matches a given target.
     * @param target the target to match.
     * @return true if matched.
     */
    public boolean isMatch(String target){
        if (atEnd()) {
            return false;
        }

        String token = peek();
        return token.equals(target);
    }

    /**
     * Test if the next token matches a given target and advances if so.
     * @param target the target to match.
     * @return true if matched.
     */
    public boolean isMatchAndAdvance(String target){
        boolean result = isMatch(target);

        if (result){
            advance();
        }
        return result;
    }

    /**
     * Match a variable if possible, advances if a variable is matched. If a variable is not
     * matched, the state of the tokeniser is unchanged.
     * @return The variable if matched, null otherwise.
     */
    public String peekVariableAndAdvance(){
        if (atEnd()) {
            return null;
        }

        String token = peek();
        if (token.startsWith("v")){
            advance();
            return token;
        } else {
            return null;
        }
    }

    public int getPosition(){
        return currentStart;
    }
}
