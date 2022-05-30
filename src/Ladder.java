import java.util.ArrayList;

public class Ladder {

    private ArrayList<Rung> rungs = new ArrayList<>();

    public Ladder() {
    }

    public void addRung(Rung newRung){
        rungs.add(newRung);
    }

    public ArrayList<Rung> getRungs() {
        return rungs;
    }

    public void setRungs(ArrayList<Rung> rungs) {
        this.rungs = rungs;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < getRungs().size(); i++) {
            output += getRungs().get(i).toString() + "\n";
        }
        return "Number of Rungs: " + getRungs().size() + "\n" + output;
    }

    public String tseitin(){
        String output = "";

        for (int i = 0; i < getRungs().size(); i++) {
            output += getRungs().get(i).tseitin() + "\n";
        }
        return "Number of Rungs: " + getRungs().size() + "\n" + output;
    }
}
