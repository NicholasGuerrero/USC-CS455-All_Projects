// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA4
// Fall 2021

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * WordFinder
 *
 * WordFinder contains the main method that allows the user to to input a
 * sequence of letters and find all legal words that can be create from any
 * subset of those letters. An input dictionary is used as a reference for what
 * words are considered "legal".
 */
public class WordFinder {

    /**
     * main method
     *
     * @param args command-line argument
     * @throws IllegalDictionaryException
     */
    public static void main(String[] args) throws IllegalDictionaryException {

        String dictionary = "./sowpods.txt";
        try {
            if (args.length > 0) {
                dictionary = args[0];
            }
            AnagramDictionary anagramDictionary = new AnagramDictionary(dictionary);
            System.out.println("Type . to quit.");
            Scanner in = new Scanner(System.in);

            while (true) {
                System.out.print("Rack? ");
                String letters = in.nextLine();

                // User types ".", and the program exits
                if (".".equals(letters)) {
                    return;
                }

                Rack rack = new Rack(letters);
                ScoreTable scoreTable = new ScoreTable(rack, anagramDictionary);
                int anagramCount = scoreTable.getAnagrams().size();

                System.out.println("We can make " + anagramCount + " words from \"" + letters + "\"");
                if (anagramCount > 0) {
                    System.out.println("All of the words with their scores (sorted by score):");
                    scoreTable.printSorted(System.out);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Dictionary file " + dictionary + " does not exist.");
            System.out.println("Exiting program.");
            return;
        } catch (IllegalDictionaryException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Exiting program.");
            return;
        }
    }
}