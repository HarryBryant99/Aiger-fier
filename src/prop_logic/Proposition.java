package prop_logic;

public class Proposition extends Expression {

    private final String name;

    public Proposition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
