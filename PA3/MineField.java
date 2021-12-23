// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA3
// Fall 2021

import java.util.Random;

/**
 * MineField class with locations of mines for a game. This class is mutable,
 * because we sometimes need to change it once it's created. mutators:
 * populateMineField, resetEmpty includes convenience method to tell the number
 * of mines adjacent to a location.
 */
public class MineField {

   // instance variables
   private int numRows;
   private int numCols;
   private int numMines;
   private boolean[][] mineField;
   private Random generator = new Random();
   private static final int ONE_SQUARE = 1;

   /**
    * Create a minefield with same dimensions as the given array, and populate it
    * with the mines in the array such that if mineData[row][col] is true, then
    * hasMine(row,col) will be true and vice versa. numMines() for this minefield
    * will corresponds to the number of 'true' values in mineData.
    * 
    * @param mineData the data for the mines; must have at least one row and one
    *                 col, and must be rectangular (i.e., every row is the same
    *                 length)
    */
   public MineField(boolean[][] mineData) {

      this.numRows = mineData.length;
      this.numCols = mineData[0].length;
      this.mineField = new boolean[this.numRows][this.numCols];

      // assign mines to mineField and tally numMines using mineData parameter
      for (int row = 0; row < this.numRows; row++) {
         for (int col = 0; col < this.numCols; col++) {
            this.mineField[row][col] = mineData[row][col]; // populate mines into mineField
            if (hasMine(row, col)) {
               this.numMines++;
            }
         }
      }
   }

   /**
    * Create an empty minefield (i.e. no mines anywhere), that may later have
    * numMines mines (once populateMineField is called on this object). Until
    * populateMineField is called on such a MineField, numMines() will not
    * correspond to the number of mines currently in the MineField.
    * 
    * @param numRows  number of rows this minefield will have, must be positive
    * @param numCols  number of columns this minefield will have, must be positive
    * @param numMines number of mines this minefield will have, once we populate
    *                 it. PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3
    *                 of total number of field locations).
    */
   public MineField(int numRows, int numCols, int numMines) {

      this.numRows = numRows;
      this.numCols = numCols;
      this.numMines = numMines;
      this.mineField = new boolean[numRows][numCols];
   }

   /**
    * Removes any current mines on the minefield, and puts numMines() mines in
    * random locations on the minefield, ensuring that no mine is placed at (row,
    * col).
    * 
    * @param row the row of the location to avoid placing a mine
    * @param col the column of the location to avoid placing a mine PRE:
    *            inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {

      // Removes any current mines on the minefield
      resetEmpty();

      // Populate mineField
      int i = 0;
      while (i < this.numMines) {

         int mineRow = this.generator.nextInt(this.numRows);
         int mineCol = this.generator.nextInt(this.numCols);

         boolean hasMine = hasMine(mineRow, mineCol); // boolean : [ does square have mine? ]
         boolean reserved = ((row == mineRow) && (col == mineCol)); // boolean : [ is square reserved? ]

         if (hasMine || reserved) {
            continue;
         } else {
            // place mine
            this.mineField[mineRow][mineCol] = true;
            i++;
         }
      }
   }

   /**
    * Reset the minefield to all empty squares. This does not affect numMines(),
    * numRows() or numCols() Thus, after this call, the actual number of mines in
    * the minefield does not match numMines(). Note: This is the state a minefield
    * created with the three-arg constructor is in at the beginning of a game.
    */
   public void resetEmpty() {
      this.mineField = new boolean[numRows][numCols];
   }

   /**
    * Returns the number of mines adjacent to the specified mine location (not
    * counting a possible mine at (row, col) itself). Diagonals are also considered
    * adjacent, so the return value will be in the range [0,8]
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return the number of mines adjacent to the square at (row, col) PRE:
    *         inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {

      int numAdjacentMines = 0;
      numAdjacentMines += boolToInt(hasAdjacentMine(row + ONE_SQUARE, col)); // down
      numAdjacentMines += boolToInt(hasAdjacentMine(row - ONE_SQUARE, col)); // up
      numAdjacentMines += boolToInt(hasAdjacentMine(row, col + ONE_SQUARE)); // right
      numAdjacentMines += boolToInt(hasAdjacentMine(row, col - ONE_SQUARE)); // left
      numAdjacentMines += boolToInt(hasAdjacentMine(row - ONE_SQUARE, col - ONE_SQUARE)); // up-left
      numAdjacentMines += boolToInt(hasAdjacentMine(row - ONE_SQUARE, col + ONE_SQUARE)); // up-right
      numAdjacentMines += boolToInt(hasAdjacentMine(row + ONE_SQUARE, col + ONE_SQUARE)); // down-right
      numAdjacentMines += boolToInt(hasAdjacentMine(row + ONE_SQUARE, col - ONE_SQUARE)); // down-left
      return numAdjacentMines;
   }

   /**
    * Returns true iff (row,col) is a valid field location. Row numbers and column
    * numbers start from 0.
    * 
    * @param row row of the location to consider
    * @param col column of the location to consider
    * @return whether (row, col) is a valid field location
    */
   public boolean inRange(int row, int col) {
      boolean isRowValid = row < this.numRows && row >= 0;
      boolean isColValid = col < this.numCols && col >= 0;
      return isRowValid && isColValid;
   }

   /**
    * Returns the number of rows in the field.
    * 
    * @return number of rows in the field
    */
   public int numRows() {
      return this.numRows;
   }

   /**
    * Returns the number of columns in the field.
    * 
    * @return number of columns in the field
    */
   public int numCols() {
      return this.numCols;
   }

   /**
    * Returns whether there is a mine in this square
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return whether there is a mine in this square PRE: inRange(row, col)
    */
   public boolean hasMine(int row, int col) {
      return this.mineField[row][col];
   }

   /**
    * Returns the number of mines you can have in this minefield. For mines created
    * with the 3-arg constructor, some of the time this value does not match the
    * actual number of mines currently on the field. See doc for that constructor,
    * resetEmpty, and populateMineField for more details.
    * 
    * @return
    */
   public int numMines() {
      return this.numMines;
   }

   // <put private methods here>

   /**
    * Returns the number of adjacent mines
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return true if has mine, false otherwise
    */
   private boolean hasAdjacentMine(int row, int col) {
      if (inRange(row, col) && hasMine(row, col)) {
         return true;
      }
      return false;
   }

   /**
    * Returns true if bool is 1, 0 otherwise
    * 
    * @param bool true or false
    * @return 1 or 0
    */
   private int boolToInt(boolean bool) {
      if (bool) {
         return 1;
      }
      return 0;
   }
}
