import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Displays the move that the human choose.
 * 
 * A <CODE>HumanOthelloPlayer</CODE> object represents a
 * human Connect 4  player.
 */
public class HumanPlayer extends ConnectPlayer
{
	/**
	 * Constructs a human Connect 4 player.
	 * @param world the world
	 */
	public HumanPlayer(ConnectWorld world)
	{
		super(world, "Human", Color.yellow);
	}

	/**
	 * Retrieves the next play for the human.
	 * Postcondition: the returned location is an allowed play.
	 * @return the location for the next play
	 */
	public Location getPlay()
	{
		Location loc;
		do
		{
			loc = getWorld().getPlayerLocation();
		}
		while (! isAllowedPlay(loc));
		return loc;
	}
}
