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

                if (Character.isWhitespace(c2)){
                    if (len == 1){
                        throw new TokeniserException(currentStart, "Variable must contain a name");
                    } else {
                        return data.substring(currentStart, currentStart + len);
                    }
                } else if (Character.isLetterOrDigit(c2)) {
                    len++;
                } else {
                    throw new TokeniserException(currentStart + len + 1, "Variable cannot contain this '" + c2 + "'");
                }
            }

            //Reached end of string while parsing variable
            if (len == 1){
                throw new TokeniserException(currentStart, "Variable must contain a name");
            } else {
                return data.substring(currentStart, len);
            }
        }

        throw new TokeniserException(currentStart, "Unexpected character '" + c + "'");
    }

    public boolean atEnd(){
        consumeWhiteSpace();
        return currentStart == data.length();
    }

    public void advance(){
        String token = peek();
        currentStart += token.length();
    }
}
