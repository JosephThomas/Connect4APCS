import java.awt.Color;


/**
 * This represents a single piece on the board
 * 
 * @author 5101324
 * @version May 7, 2014
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: None
 */
public class Piece
{

    private Color color;


    /**
     * This is the constructor
     * 
     * @param c
     *            this sets the color of it
     */
    public Piece( Color c )
    {
        color = c;
    }


    /**
     * Gets the color of the piece.
     * 
     * @return the color of the piece
     */
    public Color getColor()
    {
        return color;
    }


    /**
     * Sets the color of the piece. Only used in case we use different rules
     * from Connect4
     * 
     * @param newColor
     *            the new color for the piece
     */
    public void setColor( Color newColor )
    {
        color = newColor;
    }
}