import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

/**
 * StupidComputerOthelloPlayer.java
 * 
 * A <CODE>StupidComputerPlayer</CODE> object represents a computer player
 * who selects a move by randomly chosing from the allowed locations.
 * 
 *  @author  Joseph Thomas
 *  @author  5101324
 *  @version 4/8/14
 *  @author  Period: 2
 *  @author  Assignment: GWOthello
 * 
 *  @author  Sources: Snarfed
 */
public class EasyAI extends ConnectPlayer
{
    /**
     * Constructs a stupid computer player.
     * @param world the world
     */
    public EasyAI(ConnectWorld world)
    {
        super(world, "Computer", Color.RED);
    }

    /**
     * Determines a randomly selected allowed move.
     * @return a random allowed more (if there is one);
     *         null otherwise
     */
    public Location getPlay()
    {
        ArrayList<Location> allowedMoves = getAllowedPlays();
        if(allowedMoves.size() != 0)
        {
            int rand = (int)(allowedMoves.size()*Math.random());
            return allowedMoves.get( rand );
        }
        return null;
    }
}
