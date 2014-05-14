import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * HumanOthelloPlayer.java
 * 
 * A <CODE>HumanOthelloPlayer</CODE> object represents a
 * human Othello player.
 */
public class HoomanPlayer extends ConnectPlayer
{
    /**
     * Constructs a human Othello player.
     * @param world the world
     */
    public HoomanPlayer(ConnectWorld world)
    {
        super(world, "Human", Color.BLACK);
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
