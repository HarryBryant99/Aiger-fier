package tptp_parser;

public class TokeniserException extends IllegalStateException {
    private int characterPos;

    public TokeniserException(int characterPos, String message) {
        super(message);
        this.characterPos = characterPos;
    }

    @Override
    public String toString() {
        return "TokeniserException @ position " + characterPos + ": " + getMessage();
    }
}
