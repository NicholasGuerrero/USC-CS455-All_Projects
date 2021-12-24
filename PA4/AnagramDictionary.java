// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA4
// Fall 2021

import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */
public class AnagramDictionary {

   /**
    * Representation invariant:
    *
    * anagramDictionary AnagramDictionary object should always have unique key
    * entries.
    * 
    * 
    */

   private HashMap<String, ArrayList<String>> anagramDictionary;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.
    * 
    * @param fileName the name of the file to read from
    * @throws FileNotFoundException      if the file is not found
    * @throws IllegalDictionaryException if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {

      File file = new File(fileName);
      Scanner in = new Scanner(file);
      this.anagramDictionary = createAnagramDictionary(in);
   }

   /**
    * Get all anagrams of the given string. This method is case-sensitive. E.g.
    * "CARE" and "race" would not be recognized as anagrams.
    * 
    * @param s string to process
    * @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      return this.anagramDictionary.get(sortString(string));
   }

   /**
    * @param in Scanner array of words from input dictionary
    * @return HashMap where the keys are a sorted string of letters and the values
    *         are an ArrayList of the possible anagrams of the key.
    *
    *         (Example) { "efilr" : [ "rifle", "flier"] } (assuming both "rifle"
    *         and "flier" are in the input dictionary)
    * @throws IllegalDictionaryException if duplicate words are found in the input
    *                                    dictionary
    */
   private HashMap<String, ArrayList<String>> createAnagramDictionary(Scanner in) throws IllegalDictionaryException {
      HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
      HashSet<String> uniqueHash = new HashSet<String>();

      while (in.hasNext()) {

         String word = in.next();
         String sortedLetters = sortString(word); // using sortedLetters of word as HashMap key
         if (!hashMap.containsKey(sortedLetters)) {
            uniqueHash.add(word);
            ArrayList<String> anagram = new ArrayList<String>(Arrays.asList(word));
            hashMap.put(sortedLetters, anagram);
         } else {
            if (uniqueHash.contains(word)) {
               throw new IllegalDictionaryException(
                     "ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
            }
            hashMap.get(sortedLetters).add(word);
         }
      }
      in.close();

      return hashMap;
   }

   /**
    * Sort the string in alphabetical order
    * 
    * @param string input string
    * @return sorted string
    */
   private String sortString(String string) {
      char[] charArray = string.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }
}
