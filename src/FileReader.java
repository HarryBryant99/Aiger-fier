import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class FileReader {
    /**
     * Takes in the filename and creates a scanner if the file exists and is readable. If not an exception is thrown
     *
     * @param filename The file that is to be read.
     * @return a list of names stored in an array list is returned.
     */
    public ArrayList readFile(String filename) {
        Scanner in = null;
        File inputFile = new File(filename);

        //Try catch block to remove the FileNotFoundException if the user enters a file not recognised by the program.
        try {
            in = new Scanner(inputFile);
            //This sets the scanner to look in the input file rather than null.
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open " + filename);
            System.exit(0);
            /* If the file the user is trying to open isn't found the exception is caught and dealt with so that the
             * program doesn't crash. The user gets an error message on screen alerting them that the file hasn't been
             * opened and therefore the program won't continue.
             */
        }

        //Array list containing the names is returned
        if (filename.equalsIgnoreCase("players.txt")){
            return readPlayers(in);
        } else {
            return readNames(in);
        }
    }

    /**
     * Returns all the current users of the system.
     *
     * @param lineIn The scanner to read the lines in the file.
     * @return The contents of the file is returned.
     */
    private ArrayList<Team> readNames(Scanner lineIn) {
        // Array list to store all the users read from the file.
        ArrayList<Team> listRead = new ArrayList<>();

        lineIn.useDelimiter(",");

        String teamname = "";
        int won = 0;
        int drawn = 0;
        int lost = 0;
        int rf = 0;
        int bf = 0;
        int ra = 0;
        int ba = 0;

        // Loop runs until there are no more rows left to read, adding each user to the array list.
        while (lineIn.hasNext()) {
            teamname = lineIn.next();
            if (teamname.contains("\r\n")) {
                teamname = teamname.substring(2);
            } else if (teamname.substring(0,1).equalsIgnoreCase("\n")){
                teamname = teamname.substring(1);
            }

            won = lineIn.nextInt();
            drawn = lineIn.nextInt();
            lost = lineIn.nextInt();
            rf = lineIn.nextInt();
            bf = lineIn.nextInt();
            ra = lineIn.nextInt();
            ba = lineIn.nextInt();

            Team newTeam = new Team(teamname, won, drawn, lost, rf, bf, ra, ba);
            listRead.add(newTeam);


        }
        return listRead;
    }

    /**
     * Returns all the current users of the system.
     *
     * @param lineIn The scanner to read the lines in the file.
     * @return The contents of the file is returned.
     */
    private ArrayList<Player> readPlayers(Scanner lineIn) {
        // Array list to store all the users read from the file.
        ArrayList<Player> listRead = new ArrayList<>();

        lineIn.useDelimiter(",");

        String name;
        String teamname = "";
        int matches = 0;
        int innings = 0;
        int notOuts = 0;
        double runsScored = 0;
        int balls = 0;
        double average = 0;
        int fifties = 0;
        int hundreds = 0;
        double strikeRate = 0;
        int topScore = 0;
        int ballsBowled = 0;
        int runsConceded = 0;
        int wickets = 0;
        double economy = 0;

        // Loop runs until there are no more rows left to read, adding each user to the array list.
        while (lineIn.hasNext()) {
            name = lineIn.next();
            if (name.contains("\r\n")) {
                name = name.substring(2);
            } else if (name.substring(0,1).equalsIgnoreCase("\n")){
                name = name.substring(1);
            }

            matches = lineIn.nextInt();
            innings = lineIn.nextInt();
            notOuts = lineIn.nextInt();
            runsScored = lineIn.nextDouble();
            balls = lineIn.nextInt();
            average = lineIn.nextDouble();
            fifties = lineIn.nextInt();
            hundreds = lineIn.nextInt();
            strikeRate = lineIn.nextDouble();
            ballsBowled = lineIn.nextInt();
            runsConceded = lineIn.nextInt();
            wickets = lineIn.nextInt();
            economy = lineIn.nextDouble();
            teamname = lineIn.nextLine();

            Player newPlayer = new Player(name, teamname, matches, innings, notOuts, runsScored, balls, fifties, hundreds, topScore,
                    ballsBowled, runsConceded, wickets);
            listRead.add(newPlayer);


        }
        return listRead;
    }

    public void writeTable(ArrayList<Team> listOfTeams) throws IOException {
        Writer fileWriter = new FileWriter("table.txt");
        String table = "";

        for (int i = 0; i < listOfTeams.size()-1; i++) {
            table +=listOfTeams.get(i).getTeamname() + "," +
                    listOfTeams.get(i).getWon() + "," +
                    listOfTeams.get(i).getDrawn() + "," +
                    listOfTeams.get(i).getLost() + "," +
                    listOfTeams.get(i).getRunsFor() + "," +
                    listOfTeams.get(i).getBallsFor() + "," +
                    listOfTeams.get(i).getRunsAgainst() + "," +
                    listOfTeams.get(i).getBallsAgainst() + ",\n";
        }

        table +=listOfTeams.get(listOfTeams.size()-1).getTeamname() + "," +
                listOfTeams.get(listOfTeams.size()-1).getWon() + "," +
                listOfTeams.get(listOfTeams.size()-1).getDrawn() + "," +
                listOfTeams.get(listOfTeams.size()-1).getLost() + "," +
                listOfTeams.get(listOfTeams.size()-1).getRunsFor() + "," +
                listOfTeams.get(listOfTeams.size()-1).getBallsFor() + "," +
                listOfTeams.get(listOfTeams.size()-1).getRunsAgainst() + "," +
                listOfTeams.get(listOfTeams.size()-1).getBallsAgainst() + ",";

        fileWriter.write(table);
        fileWriter.close();
    }

    public void writePlayers(ArrayList<Player> listOfPlayers) throws IOException {
        Writer fileWriter = new FileWriter("players.txt");
        String table = "";

        for (int i = 0; i < listOfPlayers.size()-1; i++) {
            table +=listOfPlayers.get(i).getName() + "," +
                    listOfPlayers.get(i).getMatches() + "," +
                    listOfPlayers.get(i).getInnings() + "," +
                    listOfPlayers.get(i).getNotOuts() + "," +
                    listOfPlayers.get(i).getRuns() + "," +
                    listOfPlayers.get(i).getBalls() + "," +
                    listOfPlayers.get(i).getAverage() + "," +
                    listOfPlayers.get(i).getFifties() + "," +
                    listOfPlayers.get(i).getHundreds() + "," +
                    listOfPlayers.get(i).getStrikeRate() + "," +
                    listOfPlayers.get(i).getTopScore() + "," +
                    listOfPlayers.get(i).getBallsBowled() + "," +
                    listOfPlayers.get(i).getWickets() + "," +
                    listOfPlayers.get(i).getEconomy() + "," +
                    listOfPlayers.get(i).getTeam() + ",\n";
        }

        table +=listOfPlayers.get(listOfPlayers.size()-1).getName() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getMatches() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getInnings() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getNotOuts() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getRuns() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getBalls() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getAverage() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getFifties() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getHundreds() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getStrikeRate() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getTopScore() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getBallsBowled() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getRunsConceded() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getWickets() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getEconomy() + "," +
                listOfPlayers.get(listOfPlayers.size()-1).getTeam() + ",";

        fileWriter.write(table);
        fileWriter.close();
    }
}
