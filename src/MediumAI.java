import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * MediumAI.java
 * 
 * A <CODE>MediumAI</CODE> object represents a computer player
 * who selects a move by randomly choosing from the allowed locations.
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 4/8/14
 *  @author  Period: 2
 *  @author  Assignment: Connect4
 * 
 *  @author  Sources: Vishwa Kode and Anshuman Dikhit
 */
public class MediumAI extends ConnectPlayer {
	/**
	 * the world that it plays on
	 */
	private ConnectWorld world;

	/**
	 * the arrayList for all the possible moves with ratings based on the red
	 * color. This is for the 
	 */
	private ArrayList<Prioritize> redMoves;

	/**
	 * the arrayList for all the possible moves with ratings based on the yellow
	 * color.
	 */
	private ArrayList<Prioritize> yellowMoves;

	/**
	 * the arrayList for all the moves with the best rating out of the two other
	 * arraylists.
	 */
	private ArrayList<Prioritize> allMoves;

	/**
	 * the color of the AI
	 */
	public Color color;

	/**
	 * Int used as an alternator between the best moves and the worst.
	 */
	public int hardness;

	/**
	 * contains all the moves arranged from best to worst
	 */
	private TreeMap<Integer, Location> move;
	/**
	 * The grid of pieces that the board displays
	 */
	private info.gridworld.grid.Grid<Piece> grid;

	/**
	 * Constructs the medium AI initializes all the instance variables calls
	 * player constructor with default parameters.
	 * 
	 * @param w
	 *            the board the game is played on
	 */
	public MediumAI(ConnectWorld w) {

		super(w, "Medium AI", Color.red);
		hardness = 0;
		color = Color.RED;
		move = new TreeMap<Integer, Location>();
		redMoves = new ArrayList<Prioritize>();
		yellowMoves = new ArrayList<Prioritize>();
		allMoves = new ArrayList<Prioritize>();
		world = w;
		grid = world.getGrid();
	}

	/**
	 * Constructs the medium AI and initializes all the instance variables calls
	 * the player constructor with the given parameters
	 * 
	 * @param w
	 *            the board the game is played on
	 * @param r
	 *            the color of the AI
	 */
	public MediumAI(ConnectWorld w, Color r) {

		super(w, "Computer", r);
		hardness = 0;

		color = r;
		redMoves = new ArrayList<Prioritize>();
        yellowMoves = new ArrayList<Prioritize>();
        allMoves = new ArrayList<Prioritize>();
		world = w;
		grid = world.getGrid();
	}

	/**
	 * Calls the getAllowedPlays() method Adds all the Locations and ratings
	 * from moves randomly to a TreeMap with the rating as the key and the
	 * Location the value. Depending on the value of the integer variable
	 * hardness it does either of two things 1 Take the last entry in theTreeMap
	 * of moves 2 Take the middle entry in the TreeMap of moves
	 * 
	 * @return returns the Location to move to
	 */
	public Location getPlay() {

		getAllowedPlays();
		if (allMoves.isEmpty()) {
			return null;
		} else {
			while (!allMoves.isEmpty()) {
				int x = (int) (Math.random() * allMoves.size());

				move.put(allMoves.get(x).getRating(), allMoves.get(x).getLocation());
				allMoves.remove(x);

			}
			int h;
			// increments the level of hardness
			if (hardness % 2 == 0) {
				h = move.size() / 2;
			} else {
			    Object[] arrMoves = move.values().toArray();
				return (Location)arrMoves[arrMoves.length -2];
			}
			hardness++;
			int count = 0;
			while (count != h) {

				move.pollLastEntry();
				count++;

			}

			return move.pollLastEntry().getValue();
		}

	}

	/**
	 * pre Condition: mapRed, mapYel, and moves have info on the previous
	 * move post Condition: mapRed, mapYel are have info on the current move and
	 * moves is empty Finds all the allowed move locations and calls rate()
	 * twice for each location one for each color. Adds those values to mapRed
	 * if the color is red and mapYel if the color is yellow. Calls the method
	 * findBest().
	 * 
	 * @return returns all the possible locations to play at
	 */
	public ArrayList<Location> getAllowedPlays() {
		while (!redMoves.isEmpty()) {
			redMoves.remove(0);
		}
		while (!yellowMoves.isEmpty()) {
			yellowMoves.remove(0);
		}
		while (!allMoves.isEmpty()) {
			allMoves.remove(0);
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

					redMoves.add(new Prioritize(mloc, c));
					// gets the rating for each location for color yellow

					int j = rate(mloc, Location.NORTH, Color.yellow)
							+ rate(mloc, Location.EAST, Color.yellow)
							+ rate(mloc, Location.NORTHEAST, Color.yellow)
							+ rate(mloc, Location.NORTHWEST, Color.yellow);

					yellowMoves.add(new Prioritize(mloc, j));
					x.add(mloc);
					break;
				}
			}
		}
		findBest();
		return x;
	}

	/**
	 * Takes the best rating for a location from mapRed and mapYel and adds that
	 * location and rating to a different arraylist called moves.
	 */
	public ArrayList<Prioritize> findBest() {
		for (int x = 0; x < redMoves.size(); x++) {
			if (redMoves.get(x).getRating() > yellowMoves.get(x).getRating()) {
				allMoves.add(redMoves.get(x));
			} else {
				allMoves.add(yellowMoves.get(x));
			}
		}
		return allMoves;
	}

	/**
	 * 
	 * @return if the player can play by checking if there are any empty
	 *         locations left.
	 */
	public boolean canPlay() {
		return !getEmptyLocations().isEmpty();
	}

	/**
	 * This assigns the ratings to the chips. It looks at chains of chips and
	 * then assigns scores based on the signs of the chips.
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
		Location old = loc;

		int count1 = 0;
		// continues while the chip is the same color
		while (grid
				.isValid(loc.getAdjacentLocation(dir + Location.HALF_CIRCLE))
				&& grid.get(loc.getAdjacentLocation(dir + Location.HALF_CIRCLE))
						.getColor().equals(c1)) {
			count1++; // increments count for each location of the same color
						// chip

			loc = loc.getAdjacentLocation(dir + Location.HALF_CIRCLE);
		}

		count1 = count1 * count1;

		int count2 = 0;
		// continues while the chip is the same color

		while ((grid.isValid(old.getAdjacentLocation(dir)) && grid
				.get(old.getAdjacentLocation(dir)).getColor().equals(c1))) {
			count2++;// increments count for each location of the same color
						// chip

			old = old.getAdjacentLocation(dir);

		}

		count2 = count2 * count2;

		if (c1.equals(color)) {
			if ((int) Math.sqrt(count2) + (int) Math.sqrt(count1) >= 3) {
				return 9999;
			}
		}

		return (count1 + count2);
	}

	// The following methods are for testing

	public ArrayList<Prioritize> getRed() {
		return redMoves;
	}

	public ArrayList<Prioritize> getYel() {
		return yellowMoves;
	}

	public ArrayList<Prioritize> getMoves() {
		return allMoves;
	}

	public TreeMap<Integer, Location> getMove() {
		return move;
	}
}
