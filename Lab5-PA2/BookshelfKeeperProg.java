// Name: Nicholas Guerrero
// USC NetID: ng55585
// CSCI455 PA2
// Fall 2021

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * BookshelfKeeperProg contains the main method that allows the user to perform
 * a series of pickPos and putHeight operations on a bookshelf in an interactive
 * mode with user commands called pick and put. It can also be run in a batch
 * mode by using input and output redirection
 */

public class BookshelfKeeperProg {
    /**
     * Representation invariant:
     * 
     */

    public static void main(String[] args)

    {

        Scanner in = new Scanner(System.in);
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();

        // Prompt user for initial input to create a pileOfBooks ArrayList
        System.out.println("Please enter initial arrangement of books followed by newline:");
        String input = in.nextLine();
        Scanner lineScanner = new Scanner(input);

        while (lineScanner.hasNext()) {
            pileOfBooks.add(lineScanner.nextInt());
        }
        lineScanner.close();

        // error check the pileOfBooks
        errorCheckBooks(pileOfBooks);

        // Create a Bookshelf and a BookshelfKeeper from the pileOfBooks and Bookshelf
        // respectively
        Bookshelf bookshelf = new Bookshelf(pileOfBooks);
        BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf);
        System.out.println(bookshelfKeeper.toString());

        // run Batch Mode
        runBatchMode(in, bookshelfKeeper);

    }

    /**
     * Check ascending order for all values of within arraylist pileOfBooks.
     */
    private static void checkAscendingOrder(ArrayList<Integer> pileOfBooks) {
        for (int i = 0; i < pileOfBooks.size() - 1; i++) {
            int prev = pileOfBooks.get(i);
            int next = pileOfBooks.get(i + 1);
            if (next < prev) {
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }

    /**
     * Check height > 0 for all values of within arraylist pileOfBooks
     */
    private static void checkHeight(ArrayList<Integer> pileOfBooks) {
        for (int i = 0; i < pileOfBooks.size(); i++) {
            if (pileOfBooks.get(i) < 0) {
                System.out.println("ERROR: Height of a book must be positive.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }

    /**
     * Check that a book being picked from a BookshelfKeeper is withint the bounds
     * of the BookshelfKeeper
     */
    private static void checkBounds(int height, BookshelfKeeper bookshelfKeeper) {
        if (height < 0 || height >= bookshelfKeeper.getNumBooks()) {
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }
    }

    /**
     * Check that a book height is not less than 0
     */
    private static void checkBookHeight(int height) {
        if (height < 0) {
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }

    }

    /**
     * Run ascending order and height error checks on arraylist pileOfBooks
     */
    private static void errorCheckBooks(ArrayList<Integer> pileOfBooks) {
        checkAscendingOrder(pileOfBooks);
        checkHeight(pileOfBooks);
    }

    /**
     * Takes user input commands and performs the releveant put, pick, end operation
     */
    private static void runBatchMode(Scanner in, BookshelfKeeper bookshelfKeeper) {
        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        while (true) {
            Scanner commandScanner = new Scanner(in.nextLine());
            String command = commandScanner.next();

            if (command.equals("end")) {
                System.out.println("Exiting Program.");
                System.exit(0);
            } else if (command.equals("put")) {
                int height = commandScanner.nextInt();
                // check book height is non-negative
                checkBookHeight(height);
                bookshelfKeeper.putHeight(height);
                System.out.println(bookshelfKeeper.toString());
            } else if (command.equals("pick")) {
                int height = commandScanner.nextInt();
                // check bounds for pick command
                checkBounds(height, bookshelfKeeper);
                bookshelfKeeper.pickPos(height);
                System.out.println(bookshelfKeeper.toString());
            } else {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                System.exit(0);
            }
        }
    }

}
