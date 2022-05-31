package prop_logic_parser;

public class ParserException extends IllegalStateException {
    private int characterPos;

    public ParserException(int characterPos, String message) {
        super(message);
        this.characterPos = characterPos;
    }

    @Override
    public String toString() {
        return "ParserException @ position " + characterPos + ": " + getMessage();
    }
}
