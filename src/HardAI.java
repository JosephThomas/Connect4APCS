import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * HardAI.java
 * 
 * A <CODE>HardAI</CODE> object represents a computer player
 * who selects a move by randomly choosing from the allowed locations.
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 5/18/14
 *  @author  Period: 2
 *  @author  Assignment: Connect4
 * 
 *  @author  Sources: Vishwa Kode and Anshuman Dikhit
 */
public class HardAI extends ConnectPlayer {
	/**
	 * the world that it plays on
	 */
	private ConnectWorld world;

	/**
	 * the arrayList for all the possible moves with ratings based on the red
	 * color.
	 */
	private ArrayList<Prioritize> mapRed;

	/**
	 * the arrayList for all the possible moves with ratings based on the yellow
	 * color.
	 */
	private ArrayList<Prioritize> mapYel;

	/**
	 * the arrayList for all the moves with the best rating out of the two other
	 * arraylists.
	 */
	private ArrayList<Prioritize> moves;

	/**
	 * the color of the AI
	 */
	public Color color;
	/**
	 * contains all the moves arranged from best to worst
	 */
	private TreeMap<Integer, Location> move;
	/**
	 * The grid of chips that the board displays
	 */
	private info.gridworld.grid.Grid<Piece> grid;

	/**
	 * Constructs the hard AI initializes all the instance variables calls
	 * player constructor with default parameters.
	 * 
	 * @param w
	 *            the board the game is played on
	 */
	public HardAI(ConnectWorld w) {

		super(w, "Hard AI", Color.red);
		color = Color.red;
		move = new TreeMap<Integer, Location>();
		mapRed = new ArrayList<Prioritize>();
		mapYel = new ArrayList<Prioritize>();
		moves = new ArrayList<Prioritize>();
		world = w;
		grid = world.getGrid();
	}

	/**
	 * Constructs the hard AI and initializes all the instance variables calls
	 * the player constructor with the given parameters
	 * 
	 * @param w
	 *            the board the game is played on
	 * @param r
	 *            the color of the AI
	 */
	public HardAI(ConnectWorld w, Color r) {

		super(w, "Computer", r);
		color = r;
		move = new TreeMap<Integer, Location>();

		mapRed = new ArrayList<Prioritize>();
		mapYel = new ArrayList<Prioritize>();
		moves = new ArrayList<Prioritize>();
		world = w;
		grid = world.getGrid();
	}

	/**
	 * Takes the best rating for a location from mapRed and mapYel and adds that
	 * location and rating to a different arraylist called moves.
	 */
	public void findBest() {
		for (int x = 0; x < mapRed.size(); x++) {
			if (mapRed.get(x).getRating() > mapYel.get(x).getRating()) {
				moves.add(mapRed.get(x));
			} else {
				moves.add(mapYel.get(x));
			}
		}
	}

	/**
	 * Calls the getAllowedPlays() method Adds all the Locations and ratings
	 * from moves randomly to a TreeMap with the rating as the key and the
	 * Location the value.
	 * 
	 * @return returns the best Location to move to
	 */
	public Location getPlay() {

		getAllowedPlays();
		if (moves.isEmpty()) {
			return null;
		} else {
			while (!moves.isEmpty()) {
				int x = (int) (Math.random() * moves.size());

				move.put(moves.get(x).getRating(), moves.get(x).getLocation());
				moves.remove(x);

			}
			// returns the best move (the last move in the map)
			return move.pollLastEntry().getValue();
		}

	}

	/**
	 * pre Condition: mapRed, mapYel, and moves are have info on the previous
	 * move post Condition: mapRed, mapYel are have info on the current move and
	 * moves is empty Finds all the allowed move locations and calls rate()
	 * twice for each location one for each color. Adds those values to mapRed
	 * if the color is red and mapYel if the color is yellow. Calls the method
	 * findBest().
	 * 
	 * @return returns all the possible locations to play at
	 */
	public ArrayList<Location> getAllowedPlays() {
		while (!mapRed.isEmpty()) {
			mapRed.remove(0);
		}
		while (!mapYel.isEmpty()) {
			mapYel.remove(0);
		}
		while (!moves.isEmpty()) {
			moves.remove(0);
		}
		int h = 0;
		ArrayList<Location> x = new ArrayList<Location>();
		for (int col = 0; col < this.getWorld().getGrid().getNumCols(); col++) {
			for (int row = grid.getNumRows() - 1; row >= 0; row--) {
				h = row;
				Location mloc = new Location(h, col);
				if (grid.get(mloc).getColor().equals(Color.white)) {
					// gets the rating for each location for color red

					int c = rate(mloc, Location.NORTH, Color.red)
							+ rate(mloc, Location.EAST, Color.red)
							+ rate(mloc, Location.NORTHEAST, Color.red)
							+ rate(mloc, Location.NORTHWEST, Color.red);

					mapRed.add(new Prioritize(mloc, c));
					// gets the rating for each location for color yellow

					int j = rate(mloc, Location.NORTH, Color.yellow)
							+ rate(mloc, Location.EAST, Color.yellow)
							+ rate(mloc, Location.NORTHEAST, Color.yellow)
							+ rate(mloc, Location.NORTHWEST, Color.yellow);

					mapYel.add(new Prioritize(mloc, j));
					x.add(mloc);
					break;
				}
			}
		}
		findBest();
		return x;
	}

	/**
	 * 
	 * @return if the player can play
	 */
	public boolean canPlay() {
		return !getEmptyLocations().isEmpty();
	}

	/**
	 * Uses a while loop that goes to the next chip in the same direction as
	 * indicated. Stops when the chip is a different color. Multiplies count by
	 * itself to differentiate between three in a row and one in a row for three
	 * directions. It does the same in the opposite direction with a different
	 * count. Adds the counts together and returns them. If the square roots of
	 * the sums add up to 3 and the color is the same as the color of the HardAI
	 * the rating 99999 is given so that the HardAI will place a move here to
	 * win.
	 * 
	 * @param loc
	 *            the location that needs to be rated
	 * @param dir
	 *            the direction to look for chips of the same color
	 * @param c1
	 *            the color of the chips
	 * @return returns the rating for the specified location
	 */
	public int rate(Location loc, int dir, Color c1) {
		Location old = loc; // saves a copy of the location for the second while
							// loop to use

		int count1 = 0;
		while (grid
				.isValid(loc.getAdjacentLocation(dir + Location.HALF_CIRCLE))
				&& grid.get(loc.getAdjacentLocation(dir + Location.HALF_CIRCLE))
						.getColor().equals(c1)) {
			count1++;
			loc = loc.getAdjacentLocation(dir + Location.HALF_CIRCLE); // gets
																		// the
																		// next
																		// location
		}

		count1 = count1 * count1;
		int count2 = 0; // new counter
		while (grid.isValid(old.getAdjacentLocation(dir))
				&& (grid.get(old.getAdjacentLocation(dir)).getColor()
						.equals(c1))) {
			count2++;
			old = old.getAdjacentLocation(dir); // gets the next location
		}
		count2 = count2 * count2;
		if (c1.equals(color)) {
			if ((int) Math.sqrt(count2) + (int) Math.sqrt(count1) >= 3) {
				return 99999;
			}
			if (count1 + count2 > 0) {
				count1++; // gives preference to the AI pieces to make it
							// offensive
			}
		}
		return (count1 + count2);
	}

	// following methods for testing
	public ArrayList<Prioritize> getRed() {
		return mapRed;
	}

	public ArrayList<Prioritize> getYel() {
		return mapYel;
	}

	public ArrayList<Prioritize> getMoves() {
		return moves;
	}

	public TreeMap<Integer, Location> getMove() {
		return move;
	}

}
