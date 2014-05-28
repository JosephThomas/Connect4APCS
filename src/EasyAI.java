import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;


/**
 * EasyAI.java
 * 
 * A <CODE>EasyAI</CODE> object represents a computer player who selects a move
 * by randomly choosing from the allowed locations.
 * 
 * @author Joseph Thomas
 * @author 5101324
 * @version 5/18/14
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: Vishwa Kode and Anshuman Dikhit
 */
public class EasyAI extends ConnectPlayer
{
    /**
     * Constructs an EasyAI.
     * 
     * @param world
     *            the world
     */
    public EasyAI( ConnectWorld world )
    {
        super( world, "Easy AI", Color.RED );
    }


    /**
     * 
     * @param world
     * @param c
     *            the color of the AI
     */
    public EasyAI( ConnectWorld world, Color c )
    {
        super( world, "Computer", c );
    }


    


    /**
     * Determines a randomly selected allowed move.
     * 
     * @return a random allowed more (if there is one); null otherwise
     */
    public Location getPlay()
    {

        ArrayList<Location> allowedMoves = this.getAllowedPlays();
        if ( allowedMoves.size() != 0 )
        {
            int rand = (int)( allowedMoves.size() * Math.random() );
            return allowedMoves.get( rand );
        }
        return null;

    }
    
    /**
     * Determines all the possible move locations.
     * 
     * @return an arrayList if all the possible locations
     */
    public ArrayList<Location> getAllowedPlays()
    {
        ArrayList<Location> x = new ArrayList<Location>();
        for ( int z = 0; z < this.getWorld().getGrid().getNumCols(); z++ )
        {
            for ( int g = getWorld().getGrid().getNumRows() - 1; g >= 0; g-- )
            {
                if ( getWorld().getGrid()
                    .get( new Location( g, z ) )
                    .getColor()
                    .equals( Color.white ) )
                {
                    x.add( new Location( g, z ) );
                    break;
                }

            }
        }

        return x;
    }

}