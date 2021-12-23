// Name: Nicholas Guerrero
// USC NetID: ng55585
// CS 455 PA1
// Fall 2021

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class CoinSimComponent extends JComponent {
    // instance variables
    private int trials;
    private int twoHeads;
    private int twoTails;
    private int oneHeadOneTail;

    // Constants
    public static final int BAR_WIDTH = 50;
    public static final int BUFFER_SPACE = 40;
    public static final Color twoHeadsCOLOR = Color.red;
    public static final Color twoTailsCOLOR = Color.blue;
    public static final Color oneHeadOneTailCOLOR = Color.green;

    public CoinSimComponent(int trials, int twoHeads, int twoTails, int oneHeadOneTail) {
        this.trials = trials;
        this.twoHeads = twoHeads;
        this.twoTails = twoTails;
        this.oneHeadOneTail = oneHeadOneTail;

    }

    public void paintComponent(Graphics g) {

        // core
        Graphics2D g2 = (Graphics2D) g;
        int consoleWidth = getWidth();
        int consoleHeight = getHeight();
        double scale = (consoleHeight - BUFFER_SPACE) / (double) 100;

        // Percents
        int twoHeadsPercent = (int) Math.round(twoHeads / (double) trials * 100);
        int twoTailsPercent = (int) Math.round(twoTails / (double) trials * 100);
        int oneHeadOneTailPercent = (int) Math.round(oneHeadOneTail / (double) trials * 100);

        // Labels
        String twoHeadsLabel = "Two Heads: " + twoHeads + " (" + twoHeadsPercent + "%)";
        String twoTailsLabel = "Two Tails: " + twoTails + " (" + twoTailsPercent + "%)";
        String oneHeadOneTailLabel = "A Head and a Tail: " + oneHeadOneTail + " (" + oneHeadOneTailPercent + "%)";

        // Bars
        Bar twoHeadsBar = new Bar(consoleHeight - BUFFER_SPACE, consoleWidth * 1 / 4, BAR_WIDTH, twoHeadsPercent, scale,
                twoHeadsCOLOR, twoHeadsLabel);
        Bar twoTailsBar = new Bar(consoleHeight - BUFFER_SPACE, consoleWidth * 3 / 4, BAR_WIDTH, twoTailsPercent, scale,
                twoTailsCOLOR, twoTailsLabel);
        Bar oneHeadOneTailBar = new Bar(consoleHeight - BUFFER_SPACE, consoleWidth * 1 / 2, BAR_WIDTH,
                oneHeadOneTailPercent, scale, oneHeadOneTailCOLOR, oneHeadOneTailLabel);

        twoHeadsBar.draw(g2);
        twoTailsBar.draw(g2);
        oneHeadOneTailBar.draw(g2);

    }

}
