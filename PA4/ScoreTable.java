// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA4
// Fall 2021

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.io.PrintStream;
import java.util.TreeMap;

/**
 * A ScoreTable with information about Scrabble scores for the anagrams that are
 * possible in a given Rack (legal words dervied from an AnagramDictionary)
 */
public class ScoreTable {
    /**
     * Representation invariant:
     *
     * scoreTable ScoreTable object should always have unique key entries.
     * 
     * The relationship between the arraylist of anagrams an the scoreTable
     * ScoreTable object should remain constant, that is the anagrams in the
     * ArrayList should be the same anagrams in the scoreTable
     * 
     * If the anagrams are not provided as input, then the relationship between the
     * rack Rack object and ArrayList anagrams should always be that the anagrams
     * can always been found within the subsets Rack object (albeit in a different
     * order of characters since the anagramdictionary will sort the subset anyway )
     */

    private ArrayList<String> anagrams;
    private TreeMap<String, Integer> scoreTable;
    private Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> in1, Map.Entry<String, Integer> in2) {
            return in2.getValue() - in1.getValue();
        }
    };
    // 'lower case letter' - 'a' = 0-25 inclusive. ex: 'd' - 'a' = 3 since internal
    // numeric codes for letters are all sequential
    private final int[] SCORES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

    /**
     * Creates a ScoreTable object using a Rack and an AnagramDictionary
     */
    public ScoreTable(Rack rack, AnagramDictionary anagramDictionary) {
        this.anagrams = createAnagrams(rack, anagramDictionary);
        this.scoreTable = createScoreTable(anagrams);
    }

    /**
     * Creates a ScoreTable object using an ArrayList of anagrams
     */
    public ScoreTable(ArrayList<String> anagrams) {
        this.anagrams = anagrams;
        this.scoreTable = createScoreTable(anagrams);
    }

    /**
     * @return Returns the legal anagrams of the letters in the Rack.
     */
    public ArrayList<String> getAnagrams() {
        return this.anagrams;
    }

    /**
     * Write concordance data to "out" in decreasing order by number of occurrences.
     * Format is one entry per line: the "number" is the Scrabble score of that
     * word.
     * 
     * @param out where to write the results.
     */
    public void printSorted(PrintStream out) {

        ArrayList<Map.Entry<String, Integer>> scoreTables = new ArrayList(this.scoreTable.entrySet());

        // Sort
        Collections.sort(scoreTables, this.comparator);

        for (Map.Entry<String, Integer> curr : scoreTables) {
            out.println(curr.getValue() + ": " + curr.getKey());
        }
    }

    /**
     * Find all anagrams of a rack that are legal words in an AnagramDictionary
     *
     * @param rack              all possible subsets of a Rack of letters
     * @param anagramDictionary AnagramDictionary used to index words from
     * @return Arraylist of anagrams found in the AnagramDictionary
     */
    private ArrayList<String> createAnagrams(Rack rack, AnagramDictionary anagramDictionary) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> subsets = rack.getSubsets();

        for (int i = 0; i < subsets.size(); i++) {
            String word = subsets.get(i);
            if (anagramDictionary.getAnagramsOf(word) != null) {
                arrayList.addAll(anagramDictionary.getAnagramsOf(word));
            }
        }
        return arrayList;

    }

    /**
     * Creates a ScoreTable from an ArrayList of Anagrams
     *
     * @param anagrams anagrams of the letters in a Rack
     * @return TreeMap of the anagrams and their correspond Scrabble Scores!
     */
    private TreeMap<String, Integer> createScoreTable(ArrayList<String> anagrams) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < anagrams.size(); i++) {
            String anagram = anagrams.get(i);
            treeMap.put(anagram, getScore(anagram));
        }
        return treeMap;
    }

    /**
     * Score of given word
     *
     * @param word word's score will be calculated according to the rules of
     *             Scrabble
     * @return score of word
     */
    private int getScore(String word) {

        word = word.toLowerCase(); // lower case word to index SCORES array properly
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            score += SCORES[word.charAt(i) - 'a'];
        }
        return score;
    }
}
