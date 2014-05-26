import info.gridworld.gui.AbstractDisplay;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


/**
 * PieceDisplay.java
 * 
 * Provides code to draw a <CODE>Piece</CODE> object
 * @author Joseph
 * @version  5/28/13
 * 
 * @sources Anshuman Dikhit, Vishwa Kode
 * 
 */
public class PieceDisplay extends AbstractDisplay
{
    private static final double PIECE_DIAMETER = .8;

    // stuff
    private static final int GRADIENT_SIZE = 50;
    private static final AffineTransform ATX = AffineTransform
                    .getScaleInstance(GRADIENT_SIZE, GRADIENT_SIZE);

    // stuff

    private Shape body;


    /**
     * Constructs an object that knows how to draw a piece.
     */
    public PieceDisplay()
    {
        double pieceDiameter = PIECE_DIAMETER;
        float halfLength = (float)pieceDiameter / 2;
        body = new Ellipse2D.Double( -halfLength,
            -halfLength,
            pieceDiameter,
            pieceDiameter );
    }


    /**
     * Draw a piece.
     * 
     * @param obj
     *            the piece to draw
     * @param comp
     *            not used
     * @param g2
     *            the graphics 2D object to draw with
     */
    public void draw( Object obj, Component comp, Graphics2D g2 )
    {
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON );

        Piece piece = (Piece)obj;
        Color pieceColor = piece.getColor();
        g2.setPaint( pieceColor );
        g2.draw( body );
        g2.fill( body );
        // stuff
        g2.setPaint( Color.YELLOW );
        double pieceDiameter = PIECE_DIAMETER;
        float halfLength = (float)pieceDiameter / 2;
        Shape rect = new Rectangle2D.Double( -2 * halfLength,
            -2 * halfLength,
            2 * pieceDiameter,
            2 * pieceDiameter );
        g2.setColor( Color.YELLOW );
        g2.draw( rect );
        g2.setPaint( new GradientPaint( -GRADIENT_SIZE / 4,
            -GRADIENT_SIZE / 2,
            Color.YELLOW,
            GRADIENT_SIZE / 4,
            GRADIENT_SIZE / 4,
            Color.ORANGE ) );
        g2.fill( ATX.createTransformedShape( rect ) );
        g2.scale( 1.0 / GRADIENT_SIZE, 1.0 / GRADIENT_SIZE );
        // fills in the circle for the chip
        //g2.setPaint( new GradientPaint( -GRADIENT_SIZE / 4,
        //    -GRADIENT_SIZE / 2,
        //    Color.WHITE,
        //    GRADIENT_SIZE / 4,
        //    GRADIENT_SIZE / 4,
        //    pieceColor ) );
        g2.setPaint( pieceColor );
        g2.fill( ATX.createTransformedShape( body ) );

        //g2.scale( GRADIENT_SIZE, GRADIENT_SIZE );
        // stuff
    }
}
