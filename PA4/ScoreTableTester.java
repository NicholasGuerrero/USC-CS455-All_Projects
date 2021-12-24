import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ScoreTableTester {

    public static void main(String[] args) throws FileNotFoundException, IllegalDictionaryException {

        System.out.println("=====Testing AnagramDictionary class initialization=====");
        AnagramDictionary AnagramDictionary = new AnagramDictionary("./sowpods.txt");
        Rack rack1 = new Rack("cmal");
        ScoreTable ScoreTable1 = new ScoreTable(rack1, AnagramDictionary);
        ScoreTable1.printSorted(System.out);

        ArrayList<String> test2 = AnagramDictionary.getAnagramsOf("dgo");
        ScoreTable ScoreTable2 = new ScoreTable(test2);
        ScoreTable2.printSorted(System.out);

    }

}
