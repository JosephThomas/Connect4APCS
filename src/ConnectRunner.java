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
 *  @author  Sources: None
 */
public class ConnectRunner {
    public static void main(String[] args) {
        ConnectGame n = new ConnectGame();
        n.playGame();
    }
}