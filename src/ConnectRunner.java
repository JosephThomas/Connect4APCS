import java.util.Scanner;

/**
 * ConnectRunner.java
 * 
 * Application that creates a new Connect game and plays it. It displays the
 * correct messages and lets the player choose the level of difficulty of
 * the AI
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 5/14/14
 *  @author  Period: 2
 *  @author  Assignment: CS Final Project
 * 
 *  @author  Sources: Anshuman Dikhit, Vishwa Kode
 */
public class ConnectRunner {
    /**
     * Asks for difficulty that the player wants and then creates a match with
     * the opponent being that difficulty
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Connect4");
        System.out.println("Rules: ");
        System.out
                .println("Click on a column to place a chip at the bottom most slot possible");
        System.out
                .println("You win when you get four in a row, diagonally, vertically, or horizontally");
        System.out.println("Please enter your difficulty");
        System.out.println("To enter difficulty type easy, medium, or hard");

        System.out.print(" ");
        int f = 0;
        while (f == 0) {
            boolean b = false;
            Scanner console = new Scanner(System.in);

            String diff = console.next();
            while (!b) {
                if (diff.equals("hard") || diff.equals("medium")
                        || diff.equals("easy")) {
                    b = true;
                } else if (diff.equalsIgnoreCase("quit")) {
                    f = 1;
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