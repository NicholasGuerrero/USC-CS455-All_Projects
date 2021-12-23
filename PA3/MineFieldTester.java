public class MineFieldTester {

    public static void main(String[] args) {

        boolean[][] mineData = new boolean[9][9];
        mineData[3][3] = true;
        mineData[2][7] = true;
        mineData[5][5] = true;
        mineData[1][3] = true;
        mineData[7][3] = true;

        System.out.println("=====Testing minedata minefield initialization=====");
        MineField mineField = new MineField(mineData);

        MineField mineField2 = new MineField(9, 9, 10);

        System.out.println("=====Done=====");

        System.out.println("=====populateMineField=====");
        mineField2.populateMineField(0, 0);

        System.out.println("=====Testing inRange=====");
        System.out.println("[exp: false] " + mineField.inRange(13, 13)); // false
        System.out.println("[exp: true] " + mineField.inRange(5, 6)); // true

        System.out.println("=====Testing Adjacent=====");
        System.out.println("[exp:2] " + mineField.numAdjacentMines(2, 3));
        System.out.println("[exp:1] " + mineField.numAdjacentMines(6, 2));
        System.out.println("[exp:1] " + mineField.numAdjacentMines(6, 3));
        System.out.println("[exp:2] " + mineField.numAdjacentMines(6, 4));
        System.out.println("[exp:1] " + mineField.numAdjacentMines(7, 2));
        System.out.println("[exp:0] " + mineField.numAdjacentMines(7, 3));
        System.out.println("[exp:1] " + mineField.numAdjacentMines(7, 4));
        System.out.println("[exp:0] " + mineField.numAdjacentMines(7, 7));
        System.out.println("[exp:0] " + mineField.numAdjacentMines(0, 0));

    }

}
