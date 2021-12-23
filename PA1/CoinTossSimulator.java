// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA1
// Fall 2021

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
   private Random generator = new Random();
   private int twoHeads;
   private int oneHeadOneTail;
   private int twoTails;

   /**
    * Creates a coin toss simulator with no trials done yet.
    */
   public CoinTossSimulator() {
      twoHeads = 0;
      twoTails = 0;
      oneHeadOneTail = 0;

   }

   /**
    * Runs the simulation for numTrials more trials. Multiple calls to this method
    * without a reset() between them *add* these trials to the current simulation.
    * 
    * @param numTrials number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      for (int i = 1; i <= numTrials; i++) {
         int coin1 = generator.nextInt(2); // use 2 as input to nextInt method input to represent a head and tail side
         int coin2 = generator.nextInt(2); // generates either a 0 or 1

         // it is possible to get 0,0 ; 0, 1 ; 1, 0 ; 1, 1 so add result, if 0 two heads,
         // if 1 one head one tail, if 2 two tails
         if (coin1 + coin2 == 0)
            twoHeads++;
         else if (coin1 + coin2 == 1)
            oneHeadOneTail++;
         else if (coin1 + coin2 == 2)
            twoTails++;

      }

   }

   /**
    * Get number of trials performed since last reset.
    */
   public int getNumTrials() {
      return getTwoHeads() + getTwoTails() + getHeadTails();
   }

   /**
    * Get number of trials that came up two heads since last reset.
    */
   public int getTwoHeads() {
      return twoHeads;
   }

   /**
    * Get number of trials that came up two tails since last reset.
    */
   public int getTwoTails() {
      return twoTails;
   }

   /**
    * Get number of trials that came up one head and one tail since last reset.
    */
   public int getHeadTails() {
      return oneHeadOneTail;
   }

   /**
    * Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      twoHeads = 0;
      twoTails = 0;
      oneHeadOneTail = 0;

   }

}
