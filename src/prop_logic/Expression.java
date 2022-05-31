package prop_logic;

public abstract class Expression {
    public abstract Expression cloneWithoutDisjunctions();

    public abstract Expression cloneRemovingDoubleNegation();
}
