public class Main {

    private static FileReader fileReader = new FileReader();

    private static Ladder ladder;

    public static void main(String[] args) {
        ladder = fileReader.readFile("Test2.tptp");

        //System.out.println(ladder.toString());
        System.out.println(ladder.tseitin());
    }
}
