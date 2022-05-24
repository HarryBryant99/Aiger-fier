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
    public Ladder readFile(String filename) {
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

        return readLadder(in);
    }

    /**
     * Returns all the current users of the system.
     *
     * @param lineIn The scanner to read the lines in the file.
     * @return The contents of the file is returned.
     */
    private Ladder readLadder(Scanner lineIn) {
        Ladder newLadder = new Ladder();

        lineIn.useDelimiter(" ");

        String primeVar = "";
        String operand = "";

        int counter = 0;

        // Loop runs until there are no more rows left to read, adding each rung to the ladder.
        while (lineIn.hasNext()) {
            counter++;

            primeVar = lineIn.next();
            primeVar = lineIn.next();

            operand = lineIn.nextLine();
            operand = operand.substring(5, operand.length()-2);

            Rung newRung = new Rung(primeVar, operand);
            newLadder.addRung(newRung);
        }
        return newLadder;
    }
}
