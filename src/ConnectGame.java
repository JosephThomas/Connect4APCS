import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * OthelloGame.java
 * 
 * An <CODE>OthelloGame</CODE> object represents an Othello game.
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 4/8/14
 *  @author  Period: 2
 *  @author  Assignment: GWOthello
 * 
 *  @author  Sources: Snarfed
 */
public class ConnectGame
{
    /** The world */
    private ConnectWorld world;

    /** The array of two players (human, computer) */
    private ConnectPlayer[] players;

    /** The index into players for the next player to play */
    private int playerIndex;
    
    /**
     * Constructs an Othello game and displays the grid<br>
     */
    public ConnectGame()
    {
        this(true);
    }

    /**
     * Constructs an Othello game.<br>
     * Postcondition: <CODE>players.length == 2</CODE>;
     *     <CODE>players[0]</CODE> contains a human Othello player;
     *     <CODE>players[1]</CODE> contains a computer Othello player;
     *     The world has been shown.
     *     
     *  @param show if true world is displayed. Used for testing
     */
    public ConnectGame(boolean show)
    {
        world = new ConnectWorld(this);
        players = new ConnectPlayer[2];
        players[0] = new HumanConnectPlayer(world);
        players[1] = new StupidComputerConnectPlayer(world);
        playerIndex = 0;
        
        
        if (show)
        {
            world.show();
        }
    }

    /**
     * Plays the game until it is over
     * (no player can play).
     */
    public void playGame()
    {
        while(players[playerIndex].canPlay())
        {
            players[playerIndex].play();
            playerIndex = Math.abs(playerIndex-1);
            world.setMessage(toString());
        }
    }

    /**
     * Creates a string with the current game state.
     * (used for the GUI message).
     */
    public String toString()
    {
        int numBlue = 0;
        int numRed = 0;

        Grid<Piece> board = world.getGrid();

        for (Location loc : board.getOccupiedLocations())
            if (board.get(loc).getColor().equals(Color.BLACK))
                numBlue++;
            else
                numRed++;

        String result = "Blues: " + numBlue + "    Reds: " + numRed + "\n";
        if (! players[0].canPlay() && ! players[1].canPlay())
            if (numBlue > numRed)
                result += "You won!";
            else if (numBlue < numRed)
                result += "I won!";
            else
                result += "It's a tie!";
        else
            result += players[playerIndex].getName() + " to play.";

        return result;
    }
    
   //accessors used primarily for testing
    
    protected OthelloWorld getOthelloWorld()
    {
        return world;
    }
    
    protected OthelloPlayer[] getOthelloPlayer()
    {
        return players;
    }
    
    protected int getPlayerIndex()
    {
        return playerIndex;
    }
}
