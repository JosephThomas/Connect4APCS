import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;


/**
 * Connect4 tests: ConnectGame, ConnectWorld, Piece, Prioritize, EasyAI, HardAI,
 * ConnectPlayer, HumanPlayer, MediumAI, ConnectRunner
 * 
 * 
 * @author Joseph Thomas
 * @version 5/30/13
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: Vishwa Kode, Anshuman Dikhit
 * 
 */
public class JUC4
{
    /**
     * Game tests: 
     * gameEasyConstruct - tests the constructor
     * playGame- plays the game till someone wins, make sure win works
     * won1 - checks if hasWon worked correctly in a situation
     * won2 - checks if hasWon worked correctly in a situation
     * won3 - checks if hasWon worked correctly in a situation
     * gameString - makes sure value returned isn't null
     */

    ConnectGame testingGame1 = new ConnectGame( false, "easy" );


    @Test
    public void gameEasyConstruct()
    {
        ConnectPlayer[] p = testingGame1.getPlayer();
        assertEquals( p[1] instanceof EasyAI, true );
    }


    @Test
    public void playGame()
    {
        ConnectPlayer[] p = testingGame1.getPlayer();
        p[0] = new HardAI( testingGame1.getWorld(), Color.yellow );
        testingGame1.playGame();
        assertEquals( testingGame1.getF(), -1 );
    }


    @Test
    public void won1()
    {
        testingGame1.getWorld().add( new Location( 5, 0 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 5, 1 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 5, 2 ), new Piece( Color.red ) );
        assertEquals( testingGame1.hasWon( new Location( 5, 2 ), Location.EAST, Color.red ),
            false );

    }


    public void won2()
    {
        testingGame1.getWorld().add( new Location( 5, 0 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 4, 1 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 3, 2 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 2, 3 ), new Piece( Color.red ) );
        assertEquals( testingGame1.hasWon( new Location( 5, 0 ), Location.EAST, Color.red ),
            true );

    }


    public void won3()
    {
        testingGame1.getWorld().add( new Location( 5, 0 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 4, 0 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 3, 0 ), new Piece( Color.red ) );
        testingGame1.getWorld().add( new Location( 2, 0 ), new Piece( Color.red ) );

        assertEquals( testingGame1.hasWon( new Location( 2, 0 ), Location.EAST, Color.red ),
            false );

    }


    @Test
    public void gameString()
    {
        ConnectPlayer[] player = testingGame1.getPlayer();
        player[0] = new HardAI( testingGame1.getWorld(), Color.yellow );
        testingGame1.playGame();
        assertNotNull( testingGame1.toString() );
    }

    // --Test Board
    /**
     * Board tests: 
     * boardConstruct - constructs Board and then makes sure
     * toString isn't null 
     * locClick -checks if returns location that is clicked
     * setLoc - makes sure that it can set a location
     * getPlayerLoc - compares value returned to constructed value 
     * BoardSetMessage - compares value returned to constructed value
     * 
     */
    ConnectGame testingGame2 = new ConnectGame( false, "easy" );

    ConnectWorld testingWorld1 = new ConnectWorld( testingGame2 );


    @Test
    public void boardConstruct()
    {
        assertNotNull( testingWorld1.getMessage() );
    }


    @Test
    public void locClick()
    {

        assertEquals( testingWorld1.locationClicked( new Location( 3, 0 ) ), true );
    }


    @Test
    public void setLoc()
    {
        testingWorld1.setPlayerLocation( new Location( 3, 0 ) );
        Location f = testingWorld1.getPlayerLocation();
        assertEquals( f, new Location( 3, 0 ) );
    }


    @Test
    public void getPlayerLoc()
    {
        testingWorld1.locationClicked( new Location( 3, 0 ) );
        assertEquals( testingWorld1.getPlayerLocation(), new Location( 5, 0 ) );
    }


    @Test
    public void BoardSetMessage()
    {
        testingWorld1.setMessage( "yay" );
        assertEquals( testingWorld1.getMessage(), "yay" );

    }

    // --Test Chip
    /**
     * Chip tests: GameConstructor - constructs Chip and compares the value
     * returned to the constructed value ChipGetColor - compares value returned
     * to constructed value ChipSetColor - compares value returned to
     * constructed value
     */
    // --Test PriceComparator

    Piece c = new Piece( Color.red );


    @Test
    public void ChipConstructor()
    {

        assertEquals( c.getColor(), Color.red );

    }


    @Test
    public void ChipGetColor()
    {
        assertEquals( c.getColor(), Color.red );

    }


    @Test
    public void ChipSetColor()
    {
        c.setColor( Color.yellow );
        assertEquals( c.getColor(), Color.yellow );

    }

    // --Test Moves
    /**
     * Move tests: GameConstructor - constructs Move and then compare
     * constructed value to the value returned MoveGetLocation - compares value
     * returned to constructed value MoveGetY - compares value returned to
     * constructed value
     */
    Prioritize x = new Prioritize( new Location( 5, 6 ), 7 );


    @Test
    public void MoveConstructor()
    {

        assertEquals( x.getLocation(), new Location( 5, 6 ) );

    }


    @Test
    public void MoveGetLocation()
    {

        assertEquals( x.getLocation(), new Location( 5, 6 ) );

    }


    @Test
    public void MoveGetY()
    {

        assertNotNull( x.getRating() );

    }

    // --Test EasyAI
    /**
     * Game tests: EasyAIConstructorOneParameter - constructs Game and then
     * compares value returned to constructed value
     * EasyAIConstructorTwoParameter - constructs Game and then compares value
     * returned to constructed value EasyAIGetName - compares value returned to
     * constructed value EasyAIGetWorld - compares value returned to constructed
     * value EasyAIcanPlay - compares value returned to constructed value
     * EasyAIGetAllowedPlays - compares value returned to constructed value
     * EasyAIIsAllowedPlay - compares value returned to constructed value
     * EasyAIIsAllowedPlay - compares value returned to constructed value
     * EasyAIGetPlay - compares value returned to constructed value EasyAIPlay -
     * subtracts known value & compares result
     */
    ConnectGame ga = new ConnectGame( false, "easy" );

    ConnectWorld j = new ConnectWorld( ga );

    EasyAI e = new EasyAI( j, Color.yellow );

    EasyAI y = new EasyAI( j );


    @Test
    public void EasyAIConstructorOneParameter()
    {

        assertEquals( y.getColor(), Color.red );

    }


    @Test
    public void EasyAIConstructorTwoParameter()
    {

        assertEquals( e.getColor(), Color.yellow );

    }


    @Test
    public void EasyAIGetName()
    {

        assertEquals( y.getName(), "Easy AI" );

    }


    @Test
    public void EasyAIGetWorld()
    {

        assertEquals( y.getWorld(), j );

    }


    @Test
    public void EasyAIcanPlay()
    {

        assertEquals( y.canPlay(), true );

    }


    @Test
    public void EasyAIGetAllowedPlays()
    {

        assertEquals( y.getAllowedPlays().size(), 7 );

    }


    @Test
    public void EasyAIIsAllowedPlay()
    {

        assertEquals( y.isAllowedPlay( new Location( 1, 2 ) ), false );

    }


    @Test
    public void EasyAIGetEmptyLocations()
    {

        assertEquals( y.getEmptyLocations().size(), 42 );

    }


    @Test
    public void EasyAIGetPlay()
    {

        assertNotNull( y.getPlay() );

    }


    @Test
    public void EasyAIPlay()
    {

        assertEquals( j.getGrid().get( ( y.play() ) ).getColor(), Color.red );

    }

    // --Test Medium AI
    /**
     * mediumAI tests: MediumAIConstructorOneParameter - constructs Game and
     * then compares value returned to constructed value
     * MediumAIConstructorTwoParameter - constructs MediumAI and then compares
     * value returned to constructed value MediumAIGetAllowedPlays - compares
     * value returned to constructed value MediumAIGetPlay - assures returned
     * value isn't null MediumAIFindBest - compares value returned to
     * constructed value MediumAICanPlay - compares value returned to
     * constructed value MediumAIRate - compares value returned to constructed
     * value
     */
    ConnectGame g = new ConnectGame( false, "easy" );

    ConnectWorld o = new ConnectWorld( g );

    MediumAI m = new MediumAI( o, Color.yellow );

    MediumAI me = new MediumAI( o );


    @Test
    public void MediumAIConstructorOneParameter()
    {

        assertEquals( me.getColor(), Color.red );

    }


    @Test
    public void MediumAIConstructorTwoParameter()
    {

        assertEquals( m.getColor(), Color.yellow );
    }


    @Test
    public void MediumAIGetAllowedPlays()
    {
        me.getAllowedPlays();
        assertEquals( me.getRed().get( 0 ).getLocation(), new Location( 5, 0 ) );
    }


    @Test
    public void MediumAIGetPlay()
    {

        assertNotNull( me.getPlay() );
    }


    @Test
    public void MediumAIFindBest()
    {
        me.getAllowedPlays();
        Location g;
        ArrayList<Prioritize> red = me.getRed();
        ArrayList<Prioritize> yel = me.getYel();
        if ( red.get( 0 ).getRating() > yel.get( 0 ).getRating() )
        {
            g = red.get( 0 ).getLocation();

        }
        else
        {
            g = yel.get( 0 ).getLocation();
        }
        assertEquals( me.getMoves().get( 0 ).getLocation(), g );
    }


    @Test
    public void MediumAICanPlay()
    {

        assertEquals( me.canPlay(), true );
    }


    @Test
    public void MediumAIRate()
    {

        o.add( new Location( 5, 0 ), new Piece( Color.red ) );

        assertEquals( me.rate( new Location( 4, 0 ), Location.NORTH, Color.red ),
            1 );

    }

    // --Test Hard AI
    /**
     * HardAI tests:MediumAIConstructorOneParameter - constructs Game and then
     * compares value returned to constructed value
     * HardAIConstructorTwoParameter - constructs hardAI and then compares value
     * returned to constructed value HardAIGetAllowedPlays - compares value
     * returned to constructed value HardAIGetPlay - compares value returned to
     * constructed value HardAIFindBest - compares value returned to constructed
     * value HardAICanPlay - compares value returned to constructed value
     * HardAIRate1 - compares value returned to constructed value HardAIRate2 -
     * compares value returned to constructed value HardAIRate3 - compares value
     * returned to constructed value TradeOrderGetShares - compares value
     * returned to constructed value TradeOrderGetPrice - compares value
     * returned to constructed value TradeOrderSubtractShares - subtracts known
     * value & compares result returned by getShares to expected value
     * 
     */
    ConnectGame gh = new ConnectGame( false, "easy" );

    ConnectWorld z = new ConnectWorld( g );

    HardAI h = new HardAI( z, Color.yellow );

    HardAI ha = new HardAI( z );


    @Test
    public void HardAIConstructorOneParameter()
    {

        assertEquals( ha.getColor(), Color.red );

    }


    @Test
    public void HardAIConstructorTwoParameter()
    {

        assertEquals( h.getColor(), Color.yellow );
    }


    @Test
    public void HardAIFindBest()
    {
        ha.getAllowedPlays();
        Location loc;
        ArrayList<Prioritize> red = ha.getRed();
        ArrayList<Prioritize> yel = ha.getYel();
        if ( red.get( 0 ).getRating() > yel.get( 0 ).getRating() )
        {
            loc = red.get( 0 ).getLocation();

        }
        else
        {
            loc = yel.get( 0 ).getLocation();
        }
        assertEquals( ha.getMoves().get( 0 ).getLocation(), loc );
    }


    @Test
    public void HardAIGetAllowedPlays()
    {
        ha.getAllowedPlays();
        assertEquals( ha.getRed().get( 0 ).getLocation(), new Location( 5, 0 ) );
    }


    @Test
    public void HardAIGetPlay()
    {

        z.add( new Location( 5, 0 ), new Piece( Color.red ) );
        z.add( new Location( 4, 0 ), new Piece( Color.red ) );

        assertEquals( ha.getPlay(), new Location( 3, 0 ) );
    }


    @Test
    public void HardAICanPlay()
    {

        assertEquals( ha.canPlay(), true );
    }


    @Test
    public void HardAIRate1()
    {

        z.add( new Location( 5, 0 ), new Piece( Color.red ) );

        assertEquals( ha.rate( new Location( 4, 0 ), Location.NORTH, Color.red ),
            2 );

    }


    @Test
    public void HardAIRate2()
    {
        h.getWorld().add( new Location( 5, 0 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 3, 2 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 2, 3 ), new Piece( Color.red ) );
        assertEquals( ha.rate( new Location( 4, 1 ),
            Location.NORTHEAST,
            Color.red ), 99999 );

    }


    @Test
    public void HardAIRate3()
    {
        h.getWorld().add( new Location( 5, 0 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 4, 0 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 3, 0 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 2, 0 ), new Piece( Color.red ) );

        assertEquals( ha.rate( new Location( 1, 0 ), Location.NORTH, Color.red ),
            99999 );

    }


    @Test
    public void HardAIRate4()
    {

        h.getWorld().add( new Location( 3, 2 ), new Piece( Color.red ) );
        h.getWorld().add( new Location( 2, 1 ), new Piece( Color.red ) );

        assertEquals( ha.rate( new Location( 1, 0 ),
            Location.NORTHWEST,
            Color.red ), 5 );

    }

    // --Test HumanPlayer
    /**
     * Game tests: GameConstructor - constructs Game and then compares value
     * returned to constructed value HumanPlayerIGetPlay - compares value
     * returned to constructed value
     */
    ConnectGame gz = new ConnectGame( false, "easy" );

    ConnectWorld u = new ConnectWorld( g );

    HumanPlayer hum = new HumanPlayer( z );


    @Test
    public void HumanPlayerIConstructor()
    {

        assertEquals( ha.getColor(), Color.red );

    }


    public void HumanPlayerIGetPlay()
    {

        u.locationClicked( new Location( 5, 0 ) );
        assertEquals( new Location( 5, 0 ), hum.getPlay() );

    }


    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUC4.class );
    }


    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    }
}
