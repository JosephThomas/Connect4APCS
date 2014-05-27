import java.util.Scanner;

/**
 * ConnectRunner.java
 * 
 * Application that creates a new Connect game and plays it. It displays the
 * correct messages and lets the player choose the level of difficulty of
 * the AI
 * 
 *  @author  Vishwa Kode
 *  @author  5101324
 *  @version 5/14/14
 *  @author  Period: 2
 *  @author  Assignment: CS Final Project
 * 
 *  @author  Sources: Anshuman Dikhit, Joseph Thomas
 */
public class ConnectRunner {
    /**
     * Asks for difficulty that the player wants and then creates a match with
     * the opponent being that difficulty
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Rules: ");
        System.out
                .println("Click on a column to place a chip at the bottom most slot possible");
        System.out
                .println("You win when you get four in a row, diagonally, vertically, or horizontally");
        System.out.println("To enter difficulty type easy, medium, or hard");

        System.out.print(" ");
        boolean f = true;
        while (f) {
            boolean b = false;
            @SuppressWarnings("resource")
            Scanner console = new Scanner(System.in);

            String diff = console.next();
            while (!b) {
                if (diff.equals("hard") || diff.equals("medium")
                        || diff.equals("easy")) {
                    b = true;
                } else if (diff.equalsIgnoreCase("quit")) {
                    f = false;
                    System.exit(0);
                } else {
                    System.out
                            .println("Your input was not correct, please enter again");
                }
            }
            ConnectGame x = new ConnectGame(true, diff);
            x.playGame();
        }

    }
}