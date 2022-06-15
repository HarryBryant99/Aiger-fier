package aiger;

import java.util.Objects;

public class And extends AigerComponent {

    private Integer id;
    private Integer lhs;
    private Integer rhs;

    public And(Integer id, Integer lhs, Integer rhs) {
        this.id = id;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLhs() {
        return lhs;
    }

    public Integer getRhs() {
        return rhs;
    }

    @Override
    public String toString() {
        return "And{" + id + " " + getLhs() + " " + getRhs() + '}';
    }

    public String print(){
        return id + " " + lhs + " " + rhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        And and = (And) o;
        return id.equals(and.id) && lhs.equals(and.lhs) && rhs.equals(and.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lhs, rhs);
    }
}
