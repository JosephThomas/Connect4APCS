import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

/**
 * EasyAI.java
 * 
 * A <CODE>EasyAI</CODE> object represents a computer player
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
public class EasyAI extends ConnectPlayer
{
    /**
     * Constructs an EasyAI.
     * @param world the world
     */
    public EasyAI(ConnectWorld world)
    {
        super(world, "Easy AI", Color.RED);
    }

    /**
     * Determines a randomly selected allowed move.
     * @return a random allowed more (if there is one);
     *         null otherwise
     */
    public Location getPlay()
    {
        ArrayList<Location> allowedMoves = this.getAllowedPlays();
        if(allowedMoves.size() != 0)
        {
            int rand = (int)(allowedMoves.size()*Math.random());
            return allowedMoves.get( rand );
        }
        return null;
    }
}
