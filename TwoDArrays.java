
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;


/**
 * This is the package for 2DArrays.java
 *
 * The 2DArrays program reads from 2 files.
 * It scans the file and puts the content into an array of string .
 * Then calls the generate mark function.
 * Which fills a 2d array with random numbers as strings.
 * Then displays the 2d array.in a CSV file.
 *
 * <p>This class satisfies style checkers.</p>
 *
 * @version 1.0
 * @since 2025-02-28
 */

public final class TwoDArrays {

        /**
         * Generates a 2D array of random marks as strings.
         *
         * @param rows the array of rows (students).
         * @param cols the array of columns (assignments).
         * @return a 2D array of random marks as strings.
         */
        public static String[][] generateMarks(final String[] rows, final String[] cols) {
            Random random = new Random();
            // make row num and col num the size of the array
            int rowsNum = rows.length;
            int colsNum = cols.length;
            String[][] marks = new String[rowsNum][colsNum];
            // fill the first row with rows values
            for (int i = 0; i < rowsNum; i++) {
                marks[i][0] = rows[i];
            }
            // fill the first column with cols values
            for (int i = 1; i < colsNum; i++) {
                marks[0][i] = cols[i];
            }
            String randNum;
            // fill the rest of the array with random numbers
            // between 0 and 100

            for (int i = 1; i < rowsNum; i++) {
                for (int j = 1; j < colsNum; j++) {
                    randNum = String.valueOf(random.nextGaussian() * 10 + 75);
                    // round the number to 0 decimal places
                    randNum = String.format("%.0f", Double.parseDouble(randNum));
                    marks[i][j] = randNum;
                }
            }


            return marks;
        }

    /**
     * This is a private constructor to satisfy style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private TwoDArrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args unused.
     * @throws IOException .
     */
    public static void main(final String[] args) throws IOException {
        File inputFile = new File("./students.txt");
        File inputFile2 = new File("./assignments.txt");
        Scanner input = new Scanner(inputFile);
        Scanner input2 = new Scanner(inputFile2);

        List<String> nameList = new ArrayList<String>();
        List<String> assignmentList = new ArrayList<String>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            nameList.add(line);
        }
        while (input2.hasNextLine()) {
            String line = input2.nextLine();
            assignmentList.add(line);
        }

        // make arrays
        String[] names = new String[nameList.size()];
        String[] assignments = new String[assignmentList.size()];
        // fill the array with the list
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = nameList.get(i);
        }
        for (int i = 0; i < assignmentList.size(); i++) {
            assignments[i] = assignmentList.get(i);
        }

        // call generate marks function
        String[][] marks = generateMarks(names, assignments);

        // make file
        FileWriter fileWriter = new FileWriter("./marks.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        // write to file
        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                printWriter.print(marks[i][j] + ",");
            }
            printWriter.println();
        }
        //close the scanners
        input.close();
        input2.close();
        //close the file writer
        fileWriter.close();
        printWriter.close();
    }
}
