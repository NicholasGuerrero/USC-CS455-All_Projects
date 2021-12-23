// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA1
// Fall 2021

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {
    public static final int JFRAME_HEIGHT = 500;
    public static final int JFRAME_WIDTH = 800;

    public static void main(String[] args)

    {
        int numTrials = 0;
        Scanner in = new Scanner(System.in);

        // Prompt user for input to number of trials to be run
        while (numTrials <= 0) {
            System.out.print("Enter number of trials: ");
            numTrials = in.nextInt();
            if (numTrials <= 0) {
                System.out.println("ERROR: Number entered must be greater than 0.");
            }
        }
        in.close();

        // Using CoinTossSimulator to generate data
        CoinTossSimulator simulation = new CoinTossSimulator();
        simulation.run(numTrials);

        int trials = simulation.getNumTrials();
        int twoHeads = simulation.getTwoHeads();
        int twoTails = simulation.getTwoTails();
        int oneHeadOneTail = simulation.getHeadTails();

        // Create JFrame
        JFrame frame = new JFrame();

        frame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CoinSimComponent component = new CoinSimComponent(trials, twoHeads, twoTails, oneHeadOneTail);
        frame.add(component);

        frame.setVisible(true);

    }
}
