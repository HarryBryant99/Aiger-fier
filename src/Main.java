public class Main {

    private static FileReader fileReader = new FileReader();

    private static Ladder ladder;

    public static void main(String[] args) {
        ladder = fileReader.readFile("Ladder.tptp");

        System.out.println(ladder.toString());
    }
}
