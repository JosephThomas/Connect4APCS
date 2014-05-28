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
 *  @version 5/20/14
 *  @author  Period: 2
 *  @author  Assignment: Connect4FinalProject
 * 
 *  @author  Sources: Vishwa Kode, Anshuman Dikhit
 */
public class ConnectWorld extends World<Piece> {
	/** The Connect game */
	private ConnectGame game;

	/**
	 * A clamp used to prevent getPlayerLocation from executing before
	 * setPlayerLocation
	 */
	private Semaphore lock;

	/**
	 * The last selected player location
	 */
	private Location playerLocation;

	/**
     * Construct an Othello world
     * game The Othello game
     */
	public ConnectWorld(ConnectGame game) {
		super(new BoundedGrid<Piece>(6, 7));

		this.game = game;
		lock = new Semaphore(0);
		playerLocation = null;
		setMessage("Connect4 - You are yellow.  Click on a column to play.");

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
     * Handles the mouse location click. Puts the piece in the lowest section of
     * the column
     * @param loc the location that was clicked
     * @return true because the click has been handled
     */
	@Override
	public boolean locationClicked(Location loc) {
		Location locs = loc;
		for (int x = getGrid().getNumRows() - 1; x >= 0; x--) {
			if (getGrid().get(new Location(x, loc.getCol())).getColor()
					.equals(Color.white)) {
				locs = new Location(x, loc.getCol());
				break;
			}

		}

		setPlayerLocation(locs);
		return true;
	}

	/**
	 * Sets <CODE>playerLocation</CODE>.
	 * 
	 * @param loc
	 *            the location to be used to set the player location
	 */
	void setPlayerLocation(Location loc) {
		lock.drainPermits(); // Remove all permits
		playerLocation = loc;
		lock.release(); // Allow getPlayerLocation to run once
	}

	/**
	 * Gets the last player location chosen by the human player.
	 * 
	 * @return the last location chosen by the human player
	 */
	public Location getPlayerLocation() {
		try {
			lock.acquire(); // Block until setPlayerLocation runs
			return playerLocation;
		} catch (InterruptedException e) {
			throw new RuntimeException("Had catastrophic InterruptedException");
		}
	}

	/**
	 * Sets the message in the GridWorld GUI.<br>
	 * Postcondition: At least a second has elapsed before returning.
	 * 
	 * @param msg
	 *            the message text
	 */
	@Override
	public void setMessage(String msg) {
		super.setMessage(" ");
		super.setMessage(msg);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException occurred.");
		}
	}
}
