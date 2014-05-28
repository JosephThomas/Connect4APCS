import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * ConnectGame.java
 * 
 * An <CODE>ConnectGame</CODE> object represents an Connect game. This creates a
 * board and controls the entire game and the players. It decides when the game
 * is over and all the information about the players.
 * 
 * @author Vishwa Kode
 * @author 5101324
 * @version 5/8/14
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: Anshuman Dikhit, Joseph Thomas
 */
public class ConnectGame
{
    /** The world */
    private ConnectWorld world;

    /** The array of two players, one AI and one human */
    private ConnectPlayer[] players;

    /**
     * The index for the next player to play
     */
    private int playerIndex;

    /**
     * The last move the player made.
     */
    Location loc;

    /**
     * variable ends the loop in playGame() when a player has won.
     */
    boolean win;


    /**
     * Constructs an Connect game.<br>
     * Postcondition: <CODE>players.length == 2</CODE>; <CODE>players[0]</CODE>
     * contains a human Connect player; <CODE>players[1]</CODE> contains a
     * computer Connect player; The world has been shown.
     * 
     * @param show
     *            if true world is displayed. Used for testing
     * 
     * @param s
     *            the string that decides which AI to use
     */
    public ConnectGame( boolean show, String s )
    {
        win = false;
        players = new ConnectPlayer[2];
        world = new ConnectWorld( this );

        players[0] = new HumanPlayer( world );
        if ( s.equals( "easy" ) )
        {
            players[1] = new EasyAI( world );

        }
        else if ( s.equals( "medium" ) )
        {
            players[1] = new MediumAI( world );

        }
        else
        {
            players[1] = new HardAI( world );

        }

        playerIndex = 0;

        if ( show )
        {
            world.show();
        }
    }


    /**
     * Plays the game until it is over (no more spaces on the board) or until a
     * player wins.
     */
    public void playGame()
    {
        int count = 0;
        while ( players[playerIndex].canPlay() && !win )
        {
            loc = players[playerIndex].play();
            count++;
            playerIndex = count % 2;
            world.setMessage( toString() );

        }
    }


    /**
     * Creates a string with the current game state. (used for the GUI message).
     * A GUI pops up when the game is ended, either in a win, lose, or tie
     */
    public String toString()
    {
        String result = "";
        Color c = players[((playerIndex+1)%2)].getColor();
        if ( hasWon( loc, Location.NORTH, c )
            || hasWon( loc, Location.NORTHEAST, c )
            || hasWon( loc, Location.NORTHWEST, c )
            || hasWon( loc, Location.EAST, c ) )
        {
            if ( c.equals( players[0].getColor() )  )
            {
                result += "You won!";
                JOptionPane.showMessageDialog( null,
                    "You won!",
                    "Victory!",
                    JOptionPane.INFORMATION_MESSAGE );
                win = true;

            }
            else
            {
                result += "You lose :'(";
                JOptionPane.showMessageDialog( null,
                    "You lose!",
                    "Defeat",
                    JOptionPane.INFORMATION_MESSAGE );
                win = true;
            }
        }
        else if(!players[0].canPlay() && !players[1].canPlay())
        {
            result += "It's a Tie!";
            JOptionPane.showMessageDialog( null,
                "Tie!",
                "Tie",
                JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            result += players[playerIndex].getName() + " to play.";
        }

        return result;
    }
    



    /**
     * 
     * @param loc
     *            The location to start test from.
     * @param dir
     *            The direction to test in. Goes in opposite direction too.
     * @param c1
     *            The color of the pieces to look for
     * @return returns whether or not a player has won.
     */
    public boolean hasWon( Location loc, int dir, Color c1 )
    {

        Grid<Piece> board = world.getGrid();
        int count = 1;

        while ( board.isValid( loc.getAdjacentLocation( dir
            + Location.HALF_CIRCLE ) )
            && board.get( loc.getAdjacentLocation( dir + Location.HALF_CIRCLE ) )
                .getColor()
                .equals( c1 ) )
        {
            loc = loc.getAdjacentLocation( dir + Location.HALF_CIRCLE );

        }
        while ( board.isValid( loc.getAdjacentLocation( dir ) )
            && board.get( loc.getAdjacentLocation( dir  ) )
                .getColor()
                .equals( c1 ) )
        {
            count++;
            loc = loc.getAdjacentLocation( dir );

        }
        return count >= 4;
    }


    // accessors used primarily for testing

    protected ConnectWorld getWorld()
    {
        return world;
    }


    protected ConnectPlayer[] getPlayer()
    {
        return players;
    }


    protected int getPlayerIndex()
    {
        return playerIndex;
    }


    protected int getF()
    {
        return win?-1:0;
    }
}
