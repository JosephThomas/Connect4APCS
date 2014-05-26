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
public abstract class ConnectPlayer
{
    /** The world */
    ConnectWorld world;

    /** The grid */
    Grid<Piece> bloard;

    /** The name of the player */
    String name;

    /** The color of this player's game pieces */

    Color color;


    /**
     * Constructs an ConnectPlayer object.
     * 
     * @param w
     *            the world
     * @param n
     *            the name ("Human" or "Computer")
     * @param c
     *            the color
     */
    public ConnectPlayer( ConnectWorld w, String n, Color c )
    {
        color = c;
        world = w;
        name = n;
        bloard = world.getGrid();
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
    public String getName()
    {
        return name;
    }


    /**
     * Gets the Connect world.
     * 
     * @return the Connect world
     */
    public ConnectWorld getWorld()
    {
        return world;
    }


    /**
     * Determines if the player can make a play.
     * 
     * @return true if the player can play; false otherwise
     */
    public boolean canPlay()
    {
        return getAllowedPlays().size() != 0;
    }


    /**
     * Computes the list of locations that the player may play
     * 
     * @return an arrayList of all the possible locations that a piece can be 
     * placed
     */
    public ArrayList<Location> getAllowedPlays()
    {
//        ArrayList<Location> allowedLocations = new ArrayList<Location>();
//        for ( Location empty : getEmptyLocations() )
//            for ( Location adjacent : bloard.getOccupiedAdjacentLocations( empty ) )
//            {
//                Location second = getNextLocationWithColor( empty, adjacent );
//                if ( second != null && !second.equals( adjacent ) )
//                    allowedLocations.add( empty );
//            }
//        return allowedLocations;
        ArrayList<Location> allowedLocations = new ArrayList<Location>();
        for ( int z = 0; z < 7; z++ )
        {
            for ( int g = 5; g >= 0; g-- )
            {
                if ( getWorld().getGrid()
                    .get( new Location( g, z ) )
                    .getColor()
                    .equals( Color.white ) )
                {
                    allowedLocations.add( new Location( g, z ) );
                    break;
                }

            }
        }

        return allowedLocations;
    }


    /**
     * Determines if this play is allowed by the rules
     * 
     * @param loc
     *            location to be checked
     * @return true if this location is allowed to be played; false otherwise
     */
    public boolean isAllowedPlay( Location loc )
    {
        return getAllowedPlays().contains( loc );
    }


    /**
     * Make the play indicated by calling getPlay.Changes the color of the
     * piece in that location.
     * @return 
     */
    public Location play()
    {
        Location playLocation = getPlay();
        bloard.get(playLocation).setColor(color);
        return getPlay();
    }
    

    /**
     * Returns the color of the player
     * 
     * @return color the color of the player
     */
    public Color getColor()
    {
        return color;
    }
}
