import info.gridworld.grid.Location;


/**
 * The Prioritize class holds the rating and the Location of a possible move
 * location. It helps the AIs decide on which spaces to move to.
 * 
 * @author Vishwa Kode
 * @version 5/25/13
 * @sources Anshuman Dikhit, Joseph Thomas
 * 
 */

public class Prioritize
{
    /**
     * A move location.
     */
    private Location loc;

    /**
     * The rating for that location.
     */
    private int rating;


    /**
     * 
     * @param l
     *            the Location
     * @param r
     *            the rating
     */
    public Prioritize( Location l, int r )
    {
        loc = l;
        rating = r;

    }


    /**
     * 
     * @return Returns the rating for that Location.
     */
    public Integer getRating()
    {
        return rating;
    }


    /**
     * 
     * @return Returns the location.
     */
    public Location getLocation()
    {
        return loc;
    }

}
