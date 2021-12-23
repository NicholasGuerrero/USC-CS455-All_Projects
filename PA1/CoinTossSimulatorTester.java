// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA1
// Fall 2021

public class CoinTossSimulatorTester {

    // Method to run a test
    public static int SimulationTest(int trials, int runs, CoinTossSimulator simulation) {
        boolean pass = false;
        simulation.run(trials);
        runs = runs + trials;

        if (runs != 0) {
            System.out.println("After run(" + trials + "):");
        }
        System.out.println("Number of trials [exp:" + runs + "]: " + simulation.getNumTrials());
        System.out.println("Two-head tosses: " + simulation.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulation.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulation.getHeadTails());
        if (simulation.getNumTrials() == runs) {
            pass = true;
        }
        System.out.println("Tosses add up correctly? " + pass);
        System.out.println();

        return runs;

    }

    public static void main(String[] args) {
        int trials = 0; // Counter for trials

        // call Tests
        CoinTossSimulator simulation = new CoinTossSimulator();
        System.out.println("After constructor:");
        trials = SimulationTest(0, trials, simulation);
        trials = SimulationTest(1, trials, simulation);
        trials = SimulationTest(10, trials, simulation);
        trials = SimulationTest(100, trials, simulation);

        // reset simulator
        simulation.reset();
        trials = 0;
        System.out.println("After reset:");
        trials = SimulationTest(0, trials, simulation);
        trials = SimulationTest(1000, trials, simulation);

    }

}
