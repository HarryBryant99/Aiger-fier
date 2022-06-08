package aiger;

import java.util.Objects;

public class Output extends AigerComponent {
    private Integer id;

    public Output(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Output{" + id + '}';
    }

    public String print(){
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Output output = (Output) o;
        return id.equals(output.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
