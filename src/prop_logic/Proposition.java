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
        this.name = processName(name);
    }

    private String processName(String name){
        if ((endsWithNumber(name)) && (!name.startsWith("gen") && !name.startsWith("sc"))){
            return name.substring(0,name.length()-2);
        } else {
            return name;
        }
    }

    private boolean endsWithNumber(String name){
        if (name.endsWith("_0")){
            return true;
        } else if (name.endsWith("_1")){
            return true;
        } else if (name.endsWith("_2")){
            return true;
        } else if (name.endsWith("_3")){
            return true;
        } else if (name.endsWith("_4")){
            return true;
        } else if (name.endsWith("_5")){
            return true;
        } else if (name.endsWith("_6")){
            return true;
        } else if (name.endsWith("_7")){
            return true;
        } else if (name.endsWith("_8")){
            return true;
        } else if (name.endsWith("_9")){
            return true;
        } else {
            return false;
        }
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
    public Proposition cloneWithoutConjunctions() {
        return new Proposition(name);
    }

    @Override
    public Proposition cloneRemovingDoubleNegation() {
        return new Proposition(name);
    }
}
