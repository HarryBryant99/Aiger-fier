package aiger;

public class Latch extends AigerComponent {

    private Integer id;
    private Integer computed;
    private Integer original;

    public Latch(Integer id, Integer computed, Integer original) {
        this.id = id;
        this.computed = computed;
        this.original = original;
    }

    public Integer getId() {
        return id;
    }

    public Integer getComputed() {
        return computed;
    }

    public Integer getOriginal() {
        return original;
    }

    @Override
    public String toString() {
        return "Latch{" + id + " " + computed + " " + original + '}';
    }
}
