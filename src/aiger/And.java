package aiger;

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
        return "Latch{" + id + " " + getLhs() + " " + getRhs() + '}';
    }
}
