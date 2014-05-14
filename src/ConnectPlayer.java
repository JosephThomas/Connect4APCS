import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;


/**
 * OthelloPlayer.java
 * 
 * This is the top-level class for all player classes.
 * 
 * @author Joseph Thomas
 * @author 5101324
 * @version 4/8/14
 * @author Period: 2
 * @author Assignment: GWOthello
 * 
 * @author Sources: Snarfed
 */
public abstract class ConnectPlayer
{
    /** The world */
    ConnectWorld world;

    /** The grid */
    Grid<Piece> bloard;

    /** The name of the player ("Human" or "Computer") */
    String name;

    /** The color of this player's game pieces */

    Color color;


    /**
     * Constructs an Othello player object.
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
     * Gets the Othello world.
     * 
     * @return the Othello world
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
     * Computers the list of locations that the player may play
     * 
     * @return allowed (legal) plays (locations) for this player
     */
    public ArrayList<Location> getAllowedPlays()
    {
        ArrayList<Location> allowedLocations = new ArrayList<Location>();
        for ( Location empty : getEmptyLocations() )
            for ( Location adjacent : bloard.getOccupiedAdjacentLocations( empty ) )
            {
                Location second = getNextLocationWithColor( empty, adjacent );
                if ( second != null && !second.equals( adjacent ) )
                    allowedLocations.add( empty );
            }
        return this.getEmptyLocations();
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
     * Make the play indicated by calling getPlay. (Place a piece and "flip" the
     * appropriate other pieces.)
     */
    public void play()
    {
        Location playLocation = getPlay();
        bloard.put( playLocation, new Piece( color ) );
        for ( Location adjacent : bloard.getOccupiedAdjacentLocations( playLocation ) )
            if ( getNextLocationWithColor( playLocation, adjacent ) != null )
                flipColorOfPieces( adjacent,
                    getNextLocationWithColor( playLocation, adjacent ) );
    }


    /**
     * Determines the empty locations on the board.
     * 
     * @return a list of the empty board locations
     */
    private ArrayList<Location> getEmptyLocations()
    {
        ArrayList<Location> unOccupied = new ArrayList<Location>();
        for ( int i = 0; i < bloard.getNumRows(); i++ )
        {
            for ( int j = 0; j < bloard.getNumCols(); j++ )
            {
                if ( bloard.get( new Location( i, j ) ) == null )
                {
                    unOccupied.add( new Location( i, j ) );
                }
            }
        }
        return unOccupied;
    }


    /**
     * Finds the next location with this player's color beginning with second in
     * the direction from first to second.
     * 
     * @param first
     *            first location
     * @param second
     *            second location
     * @return next location with this player's color in the direction from
     *         first to second starting with second
     */
    private Location getNextLocationWithColor( Location first, Location second )
    {
        int direction = first.getDirectionToward( second );
        while ( true )
        {
            if ( !bloard.isValid( second ) )
                return null;
            if ( bloard.get( second ) == null )
                return null;
            if ( bloard.get( second ).getColor() == color )
                return second;
            second = second.getAdjacentLocation( direction );
        }
    }


    /**
     * Changes (flips) the color of the pieces to the current player's color in
     * the locations from start (included) to end (not included)
     * 
     * @param start
     *            first location to color (flip)
     * @param end
     *            first location past last location to color (flip)
     */
    private void flipColorOfPieces( Location start, Location end )
    {
        int direction = start.getDirectionToward( end );
        bloard.get( start ).setColor( color );
        while ( !start.equals( end ) )
        {
            start = start.getAdjacentLocation( direction );
            bloard.get( start ).setColor( color );
        }
    }
}
