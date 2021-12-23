// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA3
// Fall 2021

/**
 * VisibleField class This is the data that's being displayed at any one point
 * in the game (i.e., visible field, because it's what the user can see about
 * the minefield). Client can call getStatus(row, col) for any square. It
 * actually has data about the whole current state of the game, including the
 * underlying mine field (getMineField()). Other accessors related to game
 * status: numMinesLeft(), isGameOver(). It also has mutators related to actions
 * the player could do (resetGameDisplay(), cycleGuess(), uncover()), and
 * changes the game state accordingly.
 * 
 * It, along with the MineField (accessible in mineField instance variable),
 * forms the Model for the game application, whereas GameBoardPanel is the View
 * and Controller, in the MVC design pattern. It contains the MineField that
 * it's partially displaying. That MineField can be accessed (or modified) from
 * outside this class via the getMineField accessor.
 */
public class VisibleField {
   // ----------------------------------------------------------
   // The following public constants (plus numbers mentioned in comments below) are
   // the possible states of one
   // location (a "square") in the visible field (all are values that can be
   // returned by public method
   // getStatus(row, col)).

   // The following are the covered states (all negative values):
   public static final int COVERED = -1; // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):

   // values in the range [0,8] corresponds to number of mines adjacent to this
   // square

   public static final int MINE = 9; // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10; // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11; // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------

   // <put instance variables here>
   private MineField mineField;
   private int[][] visibileField;
   int numRows;
   int numCols;

   private static final int ONE_SQUARE = 1;

   /**
    * Create a visible field that has the given underlying mineField. The initial
    * state will have all the mines covered up, no mines guessed, and the game not
    * over.
    * 
    * @param mineField the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      this.numRows = this.mineField.numRows();
      this.numCols = this.mineField.numCols();
      this.visibileField = new int[this.mineField.numRows()][this.mineField.numCols()];
      resetGameDisplay();
   }

   /**
    * Reset the object to its initial state (see constructor comments), using the
    * same underlying MineField.
    */
   public void resetGameDisplay() {
      for (int i = 0; i < this.numRows; i++) {
         for (int j = 0; j < this.numCols; j++) {
            this.visibileField[i][j] = COVERED;
         }
      }
   }

   /**
    * Returns a reference to the mineField that this VisibleField "covers"
    * 
    * @return the minefield
    */
   public MineField getMineField() {
      return this.mineField;
   }

   /**
    * Returns the visible status of the square indicated.
    * 
    * @param row row of the square
    * @param col col of the square
    * @return the status of the square at location (row, col). See the public
    *         constants at the beginning of the class for the possible values that
    *         may be returned, and their meanings. PRE: getMineField().inRange(row,
    *         col)
    */
   public int getStatus(int row, int col) {
      return this.visibileField[row][col];
   }

   /**
    * Returns the the number of mines left to guess. This has nothing to do with
    * whether the mines guessed are correct or not. Just gives the user an
    * indication of how many more mines the user might want to guess. This value
    * can be negative, if they have guessed more than the number of mines in the
    * minefield.
    * 
    * @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int numMines = getMineField().numMines();

      for (int i = 0; i < this.numRows; i++) {
         for (int j = 0; j < this.numCols; j++) {
            int square = getStatus(i, j);
            if (square == MINE_GUESS) {
               numMines--;
            }
         }
      }
      return numMines;
   }

   /**
    * Cycles through covered states for a square, updating number of guesses as
    * necessary. Call on a COVERED square changes its status to MINE_GUESS; call on
    * a MINE_GUESS square changes it to QUESTION; call on a QUESTION square changes
    * it to COVERED again; call on an uncovered square has no effect.
    * 
    * @param row row of the square
    * @param col col of the square PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      int square = getStatus(row, col);
      if (square == QUESTION) {
         this.visibileField[row][col] = COVERED;
      } else {
         this.visibileField[row][col]--; // cycle
      }
   }

   /**
    * Uncovers this square and returns false iff you uncover a mine here. If the
    * square wasn't a mine or adjacent to a mine it also uncovers all the squares
    * in the neighboring area that are also not next to any mines, possibly
    * uncovering a large region. Any mine-adjacent squares you reach will also be
    * uncovered, and form (possibly along with parts of the edge of the whole
    * field) the boundary of this region. Does not uncover, or keep searching
    * through, squares that have the status MINE_GUESS. Note: this action may cause
    * the game to end: either in a win (opened all the non-mine squares) or a loss
    * (opened a mine).
    * 
    * @param row of the square
    * @param col of the square
    * @return false iff you uncover a mine at (row, col) PRE:
    *         getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      boolean hasMine = getMineField().hasMine(row, col);
      if (hasMine) {
         this.visibileField[row][col] = EXPLODED_MINE;
         displayLoseScreen();
         return false;
      } else {
         floodFill(row, col);
      }
      return true;
   }

   /**
    * Returns whether the game is over. (Note: This is not a mutator.)
    * 
    * @return whether game over
    */
   public boolean isGameOver() {
      int totalSquares = this.numRows * this.numCols;
      int unCoveredSquareCount = 0;

      for (int i = 0; i < this.numRows; i++) {
         for (int j = 0; j < this.numCols; j++) {
            int square = getStatus(i, j);
            if (square == EXPLODED_MINE) {
               // displayLoseScreen();
               return true; // player loses!
            } else if (isUncovered(i, j)) {
               unCoveredSquareCount++;
            }
         }
      }

      if (totalSquares - getMineField().numMines() == unCoveredSquareCount) {
         return true; // player wins!
      }
      return false; // game is not over yet
   }

   /**
    * Returns whether this square has been uncovered. (i.e., is in any one of the
    * uncovered states, vs. any one of the covered states).
    * 
    * @param row of the square
    * @param col of the square
    * @return whether the square is uncovered PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      int square = getStatus(row, col);

      return square != COVERED && square != MINE_GUESS && square != QUESTION;
   }

   // <put private methods here>
   /**
    * Flood Fill DFS
    * 
    * @param row of the square
    * @param col of the square
    */
   private void floodFill(int row, int col) {

      boolean inRange = getMineField().inRange(row, col);
      if (!inRange || getMineField().hasMine(row, col) || isUncovered(row, col) || getStatus(row, col) == MINE_GUESS) {
         return;
      } else if (getMineField().numAdjacentMines(row, col) == 0) {
         this.visibileField[row][col] = getMineField().numAdjacentMines(row, col);
         floodFill(row - ONE_SQUARE, col); // up
         floodFill(row + ONE_SQUARE, col);// down
         floodFill(row, col - ONE_SQUARE);// left
         floodFill(row, col + ONE_SQUARE); // right
         floodFill(row + ONE_SQUARE, col + ONE_SQUARE);// down-right
         floodFill(row + ONE_SQUARE, col - ONE_SQUARE);// down-left
         floodFill(row - ONE_SQUARE, col + ONE_SQUARE); // up-right
         floodFill(row - ONE_SQUARE, col - ONE_SQUARE); // up-left
         return;
      } else {
         this.visibileField[row][col] = getMineField().numAdjacentMines(row, col);
         return;
      }
   }

   /**
    * when the game loses, show the mines' location by setting visibleField at i,j
    * as MINE, and showing , INCORRECT_GUESS at proper locations in visibleField
    * 
    * @return whether game over
    */
   private void displayLoseScreen() {
      int square;
      boolean hasMine;
      boolean isGuess;

      for (int i = 0; i < this.numRows; i++) {
         for (int j = 0; j < this.numCols; j++) {

            square = getStatus(i, j);
            hasMine = getMineField().hasMine(i, j); // boolean : [ does square have mine? ]
            isGuess = square == MINE_GUESS || square == QUESTION; // boolean : [ does square have
                                                                  // MINE_GUESS or QUESTION ]

            if (hasMine && !isUncovered(i, j) && !isGuess) {
               // if square has a mine and is COVERED and does not have a guess, then show a
               // black box and mark as MINE
               this.visibileField[i][j] = MINE;
            }
            if (!hasMine && isGuess) {
               // if square does not have a mine and has a MINE_GUESS or QUESTION, then show an
               // X and mark as INCORRECT_GUESS
               this.visibileField[i][j] = INCORRECT_GUESS;
            }
         }
      }
   }
}
