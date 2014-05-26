import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

import javax.swing.JOptionPane;


/**
 * ConnectGame.java
 * 
 * An <CODE>ConnectGame</CODE> object represents an Connect game. This creates a
 * board and controls the entire game and the players. It decides when the game
 * is over and all the information about the players.
 * 
 * @author Joseph Thomas
 * @author 5101324
 * @version 5/8/14
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: Anshuman Dikhit, Vishwa Kode
 */
public class ConnectGame
{
    /** The world */
    private ConnectWorld world;

    /** The array of two players (human, computer) */
    private ConnectPlayer[] players;

    /** The index into players for the next player to play */
    private int playerIndex;

    /** Used to decide if the game has ended or not */
    boolean win;

    /**
     * The last move the player made.
     */
    Location loc;


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

        players[0] = new HoomanPlayer( world );
        if ( s.equals( "easy" ) )
        {
            players[1] = new EasyAI( world );

        }
        if ( s.equals( "medium" ) )
        {
            players[1] = new MediumAI( world );

        }
        if ( s.equals( "hard" ) )
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
     * Plays the game until it is over (no player can play).
     */
    public void playGame()
    {
        while ( players[playerIndex].canPlay() && !win )
        {
            loc = players[playerIndex].getPlay();
            players[playerIndex].play();
            playerIndex = Math.abs( playerIndex - 1 );
            world.setMessage( toString() );
        }
    }


    /**
     * Creates a string with the current game state. (used for the GUI message).
     */
    public String toString()
    {
        // int numBlue = 0;
        // int numRed = 0;
        //
        // Grid<Piece> board = world.getGrid();
        //
        // for (Location loc : board.getOccupiedLocations())
        // if (board.get(loc).getColor().equals(Color.BLACK))
        // numBlue++;
        // else
        // numRed++;
        //
        // String result = "Blues: " + numBlue + "    Reds: " + numRed + "\n";
        // if (! players[0].canPlay() && ! players[1].canPlay())
        // if (numBlue > numRed)
        // result += "You won!";
        // else if (numBlue < numRed)
        // result += "I won!";
        // else
        // result += "It's a tie!";
        // else
        // result += players[playerIndex].getName() + " to play.";
        String result = "";
        Color c;
        // if (((playerIndex + 1) % 2) == 0) {
        // c = Color.BLACK;
        // } else {
        // c = Color.RED;
        // }
        c = players[Math.abs( playerIndex - 1 )].getColor();
        if ( hasWon( loc, c ))
        {
            if ( Math.abs( playerIndex - 1 ) == 0 )
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
                result += "I won!";
                JOptionPane.showMessageDialog( null,
                    "I won!",
                    "Defeat",
                    JOptionPane.INFORMATION_MESSAGE );
                win = true;
            }
        }
        else if ( !players[0].canPlay() && !players[1].canPlay() )
        {
            result += "You both lose -_-";
            JOptionPane.showMessageDialog( null,
                "Losers",
                "Defeat",
                JOptionPane.INFORMATION_MESSAGE );
            win = true;
        }
        else
        {
            result = players[playerIndex].getName() + " to play.";
        }

        return result;
    }


    /**
     * 
     * @param loc
     *            The location to start test from.
     * @param c1
     *            The color of the chips to look for
     * @return returns whether or not a player has won.
     */
    public boolean hasWon( Location loc, Color c1 )
    {
        Location old = loc;

        Grid<Piece> board = world.getGrid();
        int count = 1;

        while ( board.isValid( loc.getAdjacentLocation( Location.SOUTH) )
            && board.get( loc.getAdjacentLocation( Location.SOUTH ) )
                .getColor()
                .equals( c1 ) )
        {
            //count++;

            loc = loc.getAdjacentLocation( Location.SOUTH );

        }
        while ( board.isValid( loc.getAdjacentLocation( Location.NORTH ) )
            && board.get( loc.getAdjacentLocation( Location.NORTH ) )
                .getColor()
                .equals( c1 ) )
        {
            count++;
            //old = old.getAdjacentLocation( Location.NORTH );
            loc = loc.getAdjacentLocation( Location.NORTH );

        }
        if(count >= 4)
        {
            return true;
        }
        else
        {
            count = 0;
        }
        
        return count >= 4;
    }


    // accessors used primarily for testing

    protected ConnectWorld getConnectWorld()
    {
        return world;
    }


    protected ConnectPlayer[] getConnectPlayer()
    {
        return players;
    }


    protected int getPlayerIndex()
    {
        return playerIndex;
    }
}
