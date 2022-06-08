package aiger;

import java.util.Objects;

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

    public String print(){
        return id + " " + computed + " " + original;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Latch latch = (Latch) o;
        return id.equals(latch.id) && computed.equals(latch.computed) &&
                original.equals(latch.original);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, computed, original);
    }
}
