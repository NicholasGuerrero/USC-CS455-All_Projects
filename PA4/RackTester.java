import java.util.ArrayList;

public class RackTester {

    public static void main(String[] args) {
        System.out.println("=====Testing Rack class initialization=====");
        Rack rack1 = new Rack("");
        ArrayList<String> test1 = rack1.getSubsets();
    }

}
