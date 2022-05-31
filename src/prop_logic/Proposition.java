package prop_logic;

import java.util.Objects;

public class Proposition extends Expression {

    private final String name;

    public Proposition(String name) {
        if (name == null){
            throw new IllegalArgumentException("Proposition cannot have null name");
        }

        if (name.trim().equals("")){
            throw new IllegalArgumentException("Proposition cannot have empty name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proposition that = (Proposition) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Proposition cloneWithoutDisjunctions() {
        return new Proposition(name);
    }

    @Override
    public Proposition cloneRemovingDoubleNegation() {
        return new Proposition(name);
    }
}
