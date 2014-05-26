import java.awt.Color;


/**
 * This represents a single piece on the board
 * 
 * @author Joseph Thomas
 * @version May 7, 2014
 * @author Period: 2
 * @author Assignment: Connect4
 * 
 * @author Sources: Anshuman Dikhit, Vishwa Kode
 */
public class Piece
{

    /**
     * It's color
     */
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
     * Used to change pieces from white to red or black
     * 
     * @param newColor
     *            the new color for the piece
     */
    public void setColor( Color newColor )
    {
        color = newColor;
    }
}