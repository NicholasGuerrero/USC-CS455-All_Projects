import java.util.ArrayList;

public class TestAssert {

    public static void main(String[] args) {

        ArrayList<Integer> pileOfBooks_one = new ArrayList<Integer>();
        pileOfBooks_one.add(10);
        pileOfBooks_one.add(20);
        pileOfBooks_one.add(30);

        Bookshelf bookshelf_one = new Bookshelf(pileOfBooks_one);
        bookshelf_one.addFront(-1);

        ArrayList<Integer> pileOfBooks_two = new ArrayList<Integer>();
        pileOfBooks_two.add(-10);
        pileOfBooks_two.add(-20);
        pileOfBooks_two.add(-30);

        Bookshelf bookshelf_two = new Bookshelf(pileOfBooks_two);

    }

}
