import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * HoomanPlayer.java
 * 
 * A <CODE>HoomanPlayer</CODE> object represents a
 * human Connect player.
 */
public class HoomanPlayer extends ConnectPlayer
{
    /**
     * Constructs a human Connect player.
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
