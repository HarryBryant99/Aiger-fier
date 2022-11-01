package aiger;

import java.util.Objects;

public class Input extends AigerComponent {

    private Integer id;

    public Input(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Input{" +
                id +
                '}';
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
        Input input = (Input) o;
        return Objects.equals(id, input.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
