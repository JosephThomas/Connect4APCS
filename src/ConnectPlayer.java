import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

/**
 * ConnectPlayer.java
 * 
 * This is the top-level class for all player classes.
 * 
 * @author Joseph Thomas
 * @author 5101324
 * @version 4/8/14
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: None
 */
public abstract class ConnectPlayer {
	/** The world */
	private ConnectWorld world;

	/** The grid */
	private info.gridworld.grid.Grid<Piece> grid;

	/** The name of the player */
	private String name;

	/** The color of this player's game pieces */
	public Color color;

	/**
	 * Constructs an Connectplayer object.
	 * 
	 * @param w
	 *            the world
	 * @param n
	 *            the name ("Human" or "Computer")
	 * @param c
	 *            the color
	 */
	public ConnectPlayer(ConnectWorld w, String n, Color c) {
		world = w;
		name = n;
		color = c;
		grid = world.getGrid();
	}

	/**
	 * Gets the next move.
	 * 
	 * @return the location of the next move
	 */
	public abstract Location getPlay();

	/**
	 * Gets the player name.
	 * 
	 * @return the player name
	 */
	public String getName() {
		return name; // Replace with correct code
	}

	/**
	 * Gets the Connect world.
	 * 
	 * @return the Connect world
	 */
	public ConnectWorld getWorld() {
		return world;
	}

	/**
	 * Determines if the player can make a play.
	 * 
	 * @return true if the player can play; false otherwise
	 */
	public boolean canPlay() {
		return this.getAllowedPlays().size() != 0;
	}

	/**
	 * Computes the list of locations that the player may play
	 * 
	 * @return all the possible locations a move can be made.
	 */
	public ArrayList<Location> getAllowedPlays() {
		ArrayList<Location> locs = new ArrayList<Location>();
		locs = getEmptyLocations();

		return locs;
	}

	/**
	 * Determines if this play is allowed by the rules
	 * 
	 * @param loc
	 *            location to be checked
	 * @return true if this location is allowed to be played; false otherwise
	 */
	public boolean isAllowedPlay(Location loc) {
		return getAllowedPlays().contains(loc); // Replace with correct code
	}

	/**
     * Make the play indicated by calling getPlay.Changes the color of the
     * piece in that location.
     * @return Location returns the location that it plays
     */
	public Location play() {

		Location playLocation = getPlay();

		Piece t = new Piece(color);
		
		if (grid.get(playLocation) != null) {

			grid.get(playLocation).setColor(color);

		} else {
			grid.put(playLocation, t);
		}

	
		return playLocation;
	}

	/**
	 * Determines the empty move locations on the board.
	 * 
	 * @return all the empty locations that a move can be made on 
	 */
	ArrayList<Location> getEmptyLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int col = 0; col < grid.getNumCols(); col++) {
				Location l = new Location(row, col);

				if (grid.get(l).getColor().equals(Color.white)) {
					locs.add(l);
				}

			}
		}
		
		return locs; 
	}
	/**
	 * @return the color of the player
	 */
	public Color getColor()
	{
		return color;
	}

}