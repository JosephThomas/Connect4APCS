import info.gridworld.gui.AbstractDisplay;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

/**
 * PieceDisplay.java
 * 
 * Provides code to draw a <CODE>Piece</CODE> object
 */
public class PieceDisplay extends AbstractDisplay
{
    private static final double PIECE_DIAMETER = .8;

    private Shape body;

    /**
     * Constructs an object that knows how to draw a piece.
     */
    public PieceDisplay()
    {
    	double pieceDiameter = PIECE_DIAMETER;
        float halfLength = (float)pieceDiameter / 2;

        body = new Ellipse2D.Double(-halfLength, -halfLength,
                                    pieceDiameter, pieceDiameter);
    }

	/**
	 * Draw a piece.
	 * @param obj the piece to draw
	 * @param comp not used
	 * @param g2 the graphics 2D object to draw with
	 */
    public void draw(Object obj, Component comp, Graphics2D g2)
    {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						    RenderingHints.VALUE_ANTIALIAS_ON);

    	Piece piece = (Piece)obj;
        Color pieceColor = piece.getColor();
        g2.setPaint(pieceColor);
        g2.draw(body);
        g2.fill(body);
    }
}