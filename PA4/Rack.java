// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA4
// Fall 2021

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   /**
    * Representation invariant:
    *
    * subsets Rack object and multiplcityMap Rack object should always have unique
    * key entries.
    * 
    * The keys of the HashMap multiplicityMap should be the characters from the
    * letters, this relationship should always be maintained
    *
    * Simarly the relationship between the multiplicityMap and subsets should be
    * the same, that is the generated subsets are from the keys, values of the
    * multiplicityMap.
    */

   private String letters;
   private HashMap<Character, Integer> multiplicityMap;
   private ArrayList<String> subsets;

   /**
    * Creates a new Rack instance using the given letters. Finds the unique letters
    * and the multiplicity of each letter.
    * 
    * @param letters input String
    */
   public Rack(String letters) {
      this.letters = letters;
      this.multiplicityMap = createMultiplicityMap(letters);
      this.subsets = createSubsets();
   }

   /**
    * @return Returns the input letters into the Rack.
    */
   public String getLetters() {
      return this.letters;
   }

   /**
    * @return Returns the MultiplicityMap of the Rack.
    */
   public HashMap<Character, Integer> getMultiplicityMap() {
      return this.multiplicityMap;
   }

   /**
    * @return Returns the subsets of the Rack.
    */
   public ArrayList<String> getSubsets() {
      return this.subsets;
   }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of
    * the char unique.charAt(i). PRE: mult.length must be at least as big as
    * unique.length() 0 <= k <= unique.length()
    * 
    * @param unique a string of unique letters
    * @param mult   the multiplicity of each letter from unique.
    * @param k      the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset. Unlike the multiset in the
    *         parameters, each subset is represented as a String that can have
    *         repeated characters in it.
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) { // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos. Suppose that char is 'a'...

      String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
                                                       // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k); // append another instance of 'a' to the first part
      }

      return allCombos;
   }

   /**
    * Returns a HashMap with characters as keys and the character's multiplicity in
    * the input string as values. The keys by definition will be unique.
    *
    * @param word a String from which unique characters and multiplicity will be
    *             calculated
    * @return a map of unique characters as values and corresponding multiplicity
    *         as values
    */
   private HashMap<Character, Integer> createMultiplicityMap(String letters) {
      HashMap<Character, Integer> multiplicityMap = new HashMap<>();
      for (int i = 0; i < letters.length(); i++) {
         char character = letters.charAt(i);
         if (multiplicityMap.containsKey(character)) {
            multiplicityMap.put(character, multiplicityMap.get(character) + 1);
         } else {
            multiplicityMap.put(character, 1);
         }
      }
      return multiplicityMap;
   }

   /**
    * @return Returns a ArrayList with every entry being a subset of the letters
    *         passed into the Rack
    */
   private ArrayList<String> createSubsets() {

      String unique = "";
      int[] mult = new int[this.multiplicityMap.size()];

      int index = 0;
      for (Map.Entry<Character, Integer> entry : this.multiplicityMap.entrySet()) {
         unique += entry.getKey();
         mult[index] = entry.getValue();
         index++;
      }
      ArrayList<String> subsets = allSubsets(unique, mult, 0);

      return subsets;
   }
}
