import info.gridworld.world.World;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.concurrent.Semaphore;

/**
 * This holds all the pieces of the game, and it uses Grid World to represent
 * all the pieces in a GUI.
 * 
 * ConnectWorld.java
 * 
 * An <CODE>ConnectWorld</CODE> object represents an Connect world.
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 4/8/14
 *  @author  Period: 2
 *  @author  Assignment: GWOthello
 * 
 *  @author  Sources: Snarfed
 */
public class ConnectWorld extends World<Piece>
{
    /** The Connect game */
    private ConnectGame game;

    /** A semaphore to prevent getPlayerLocation from executing
     *  before setPlayerLocation */
    private Semaphore lock;

    /** The last selected player location */
    private Location playerLocation;

    /**
     * Construct an Othello world
     * game The Othello game
     */
    public ConnectWorld(ConnectGame game)
    {
        super(new BoundedGrid<Piece>(6, 7));

        this.game = game;
        lock = new Semaphore(0);
        playerLocation = null;
        setMessage("Connect Four - You are Black.  Click a cell to play.");
        

        System.setProperty("info.gridworld.gui.selection", "hide");
        System.setProperty("info.gridworld.gui.tooltips", "hide");
        System.setProperty("info.gridworld.gui.watermark", "hide");
        for (int x = 0; x < getGrid().getNumRows(); x++) {

            for (int y = 0; y < getGrid().getNumCols(); y++) {
                add(new Location(x, y), new Piece(Color.white));

            }
        }
    }

    /**
     * Handles the mouse location click.
     * @param loc the location that was clicked
     * @return true because the click has been handled
     */
    @Override
    public boolean locationClicked(Location loc)
    {
        setPlayerLocation(loc);
        return true;
    }

    /**
     * Sets <CODE>playerLocation</CODE>.
     * @param loc the location to be used to set the player location
     */
    private void setPlayerLocation(Location loc)
    {
        lock.drainPermits();    // Remove all permits
        playerLocation = loc;
        lock.release();         // Allow getPlayerLocation to run once
    }

    /**
     * Gets the last player location chosen by the human player.
     * @return the last location chosen by the human player
     */
    public Location getPlayerLocation()
    {
        try
        {
            lock.acquire();     // Block until setPlayerLocation runs
            return playerLocation;
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(
                "Had catastrophic InterruptedException");
        }
    }

    /**
     * Sets the message in the GridWorld GUI.<br>
     * Postcondition: At least a second has elapsed before returning.
     * @param msg the message text
     */
    @Override
    public void setMessage(String msg)
    {
        super.setMessage(msg);
        try
        { Thread.sleep(2000); }
        catch (InterruptedException e)
        { System.out.println("InterruptedException occurred."); }
    }
}
