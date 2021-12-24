import java.util.ArrayList;
import java.io.FileNotFoundException;

public class AnagramDictionaryTester {

    public static void main(String[] args) throws FileNotFoundException, IllegalDictionaryException {

        System.out.println("=====Testing AnagramDictionary class initialization=====");
        AnagramDictionary AnagramDictionary1 = new AnagramDictionary("./testFiles/tinyDictionary.txt");
        ArrayList<String> test1 = AnagramDictionary1.getAnagramsOf("");
        ArrayList<String> test2 = AnagramDictionary1.getAnagramsOf("dgo");
        ArrayList<String> test3 = AnagramDictionary1.getAnagramsOf("dog");
    }
}