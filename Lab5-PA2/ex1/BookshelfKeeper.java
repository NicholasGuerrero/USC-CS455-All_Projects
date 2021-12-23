// Name: Nicholas Guerrero
// USC NetID: ng55585
// CSCI455 PA2
// Fall 2021

import java.util.ArrayList;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a
 * bookshelf of books kept in non-decreasing order by height, with the
 * restriction that single books can only be added or removed from one of the
 * two *ends* of the bookshelf to complete a higher level pick or put operation.
 * Pick or put operations are performed with minimum number of such adds or
 * removes.
 */
public class BookshelfKeeper {

   /**
    * Representation invariant:
    *
    * sortedBookshelf should always be in ascending order. mutatorCalls should
    * always be non-negative. totalMutatorCalls should always be non-negative.
    */
   private Bookshelf sortedBookshelf;
   private int mutatorCalls;
   private int totalMutatorCalls;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      this.sortedBookshelf = null;
      isValidBookshelfKeeper();

   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      this.sortedBookshelf = sortedBookshelf;
      isValidBookshelfKeeper();

   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps
    * bookshelf sorted after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      // temp variables
      ArrayList<Integer> temp = new ArrayList<Integer>();
      mutatorCalls = 0;
      int height = sortedBookshelf.getHeight(position);

      // measure how many mutator calls from the front vs the back are needed
      int front = mutatorCallsFrontPickPos(sortedBookshelf, position);
      int back = mutatorCallsBackPickPos(sortedBookshelf, position);

      // if left calls exceed right calls, use removelast/addLast methods and track
      // mutator calls
      if (front >= back) {
         pickPosBack(back, sortedBookshelf, height, temp);
      }
      // if right calls exceed left calls, use removeFront/addFront methods and track
      // mutator calls
      else {
         pickPosFront(front, sortedBookshelf, temp);
      }

      // add mutator calls to the total mutator calls count
      totalMutatorCalls += mutatorCalls;
      isValidBookshelfKeeper();
      return mutatorCalls;

   }

   /**
    * Inserts book with specified height into the shelf. Keeps the contained
    * bookshelf sorted after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      // temp variables
      ArrayList<Integer> temp = new ArrayList<Integer>();
      mutatorCalls = 0;

      // measure how many mutator calls from the front vs the back are needed
      int front = mutatorCallsFront(sortedBookshelf, height);
      int back = mutatorCallsBack(sortedBookshelf, height);

      // if left calls exceed right calls, use removelast/addLast methods and track
      // mutator calls
      if (front >= back) {
         putHeightBack(back, sortedBookshelf, height, temp);
      }
      // if right calls exceed left calls, use removeFront/addFront methods and track
      // mutator calls
      else {
         putHeightFront(front, sortedBookshelf, height, temp);
      }

      // add mutator calls to the total mutator calls count
      totalMutatorCalls += mutatorCalls;
      isValidBookshelfKeeper();
      return mutatorCalls;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {

      isValidBookshelfKeeper();
      return totalMutatorCalls;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {

      isValidBookshelfKeeper();
      return sortedBookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String
    * containing height of all books present in the bookshelf in the order they are
    * on the bookshelf, followed by the number of bookshelf mutator calls made to
    * perform the last pick or put operation, followed by the total number of such
    * calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {

      isValidBookshelfKeeper();
      return sortedBookshelf.toString() + " " + mutatorCalls + " " + totalMutatorCalls;

   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state. (See
    * representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      if (!sortedBookshelf.isSorted()) {
         return false;
      } else if (mutatorCalls < 0) {
         return false;
      } else if (totalMutatorCalls < 0) {
         return false;
      }

      return true;

   }

   /**
    * Returns the number of mutatorCalls of the Bookshelf object from the front of
    * the Bookshelf. This method is for the extra credit points.
    */
   private int mutatorCallsFrontPickPos(Bookshelf sortedBookshelf, int position) {
      int front = 0;

      for (int i = 0; i < position; i++) {
         front++;
      }
      return front;
   }

   /**
    * Returns the number of mutatorCalls of the Bookshelf object from the back of
    * the Bookshelf. This method is for the extra credit points.
    */
   private int mutatorCallsBackPickPos(Bookshelf sortedBookshelf, int position) {
      int back = 0;

      for (int i = sortedBookshelf.size() - 1; i > position; i--) {
         back++;
      }

      return back;

   }

   /**
    * Returns the number of mutatorCalls of the Bookshelf object from the front of
    * the Bookshelf.
    */
   private int mutatorCallsFront(Bookshelf sortedBookshelf, int height) {
      int front = 0;

      for (int i = 0; i < sortedBookshelf.size(); i++) {
         if (sortedBookshelf.getHeight(i) < height)
            front++;
      }
      return front;
   }

   /**
    * Returns the number of mutatorCalls of the Bookshelf object from the back of
    * the Bookshelf.
    */
   private int mutatorCallsBack(Bookshelf sortedBookshelf, int height) {
      int back = 0;

      for (int i = sortedBookshelf.size() - 1; i > 0; i--) {
         if (sortedBookshelf.getHeight(i) > height)
            back++;
      }
      return back;
   }

   /**
    * removes books from the Bookshelf Object from the back of the Bookshelf
    */
   private void removeFromBack(int back, Bookshelf sortedBookshelf, ArrayList<Integer> temp) {
      while (back > 0) {
         temp.add(sortedBookshelf.removeLast());
         mutatorCalls++;
         back--;
      }
   }

   /**
    * Inserts books from the Bookshelf Object from the back of the Bookshelf
    */
   private void addFromBack(ArrayList<Integer> temp) {
      for (int i = temp.size() - 1; i >= 0; i--) {
         sortedBookshelf.addLast(temp.get(i));
         mutatorCalls++;
      }
   }

   /**
    * Inserts the height provided into the Bookshelf object at the correct
    * locations from the back of the Bookshelf
    */
   private void putHeightBack(int back, Bookshelf sortedBookshelf, int height, ArrayList<Integer> temp) {

      // remove sortedBookshelf from the back
      removeFromBack(back, sortedBookshelf, temp);

      // add the book at correct position, increment mutatorCalls
      sortedBookshelf.addLast(height);
      mutatorCalls++;

      // add the removed values back into the arraylist (from the back)
      addFromBack(temp);
   }

   /**
    * Removes the height provided into the Bookshelf object at the correct
    * locations from the back of the Bookshelf
    */
   private void pickPosBack(int back, Bookshelf sortedBookshelf, int height, ArrayList<Integer> temp) {

      // remove sortedBookshelf from the back
      removeFromBack(back, sortedBookshelf, temp);

      // remove the book at correct position, increment mutatorCalls
      sortedBookshelf.removeLast();
      mutatorCalls++;

      // add the removed values back into the arraylist (from the back)
      addFromBack(temp);
   }

   /**
    * removes books from the Bookshelf Object from the front of the Bookshelf
    */
   private void removeFromFront(int front, ArrayList<Integer> temp) {
      while (front > 0) {
         temp.add(sortedBookshelf.removeFront());
         mutatorCalls++;
         front--;
      }
   }

   /**
    * Inserts books from the Bookshelf Object from the front of the Bookshelf
    */
   private void addFromFront(ArrayList<Integer> temp) {
      for (int i = temp.size() - 1; i >= 0; i--) {
         sortedBookshelf.addFront(temp.get(i));
         mutatorCalls++;
      }
   }

   /**
    * Removes the height provided into the Bookshelf object at the correct
    * locations from the front of the Bookshelf
    */
   private void pickPosFront(int front, Bookshelf sortedBookshelf, ArrayList<Integer> temp) {

      // remove books from the front of the Bookshelf
      removeFromFront(front, temp);

      // remove the book at correct position, increment mutatorCalls
      sortedBookshelf.removeFront();
      mutatorCalls++;

      // add the removed values back into the arraylist (from the front)
      addFromFront(temp);

   }

   /**
    * Inserts the height provided into the Bookshelf object at the correct
    * locations from the front of the Bookshelf
    */
   private void putHeightFront(int front, Bookshelf sortedBookshelf, int height, ArrayList<Integer> temp) {

      // remove books from the front of the Bookshelf
      removeFromFront(front, temp);

      // add the book at correct position, increment mutatorCalls
      sortedBookshelf.addFront(height);
      mutatorCalls++;

      // add the removed values back into the arraylist (from the front)
      addFromFront(temp);

   }

}
