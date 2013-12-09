
package UI;

import Engine.Engine;
import Engine.Suunta;
import java.awt.*;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilja
 */
public class GameBoardTest {
    
    Engine engine;
    GameBoard board;
    
    @Before
    public void setUp() {
        engine = new Engine();
        board = new GameBoard(engine);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of paintComponent method, of class GameBoard.
     */
//    @Test
//    public void testPaintComponent() {
//        System.out.println("paintComponent");
//        Graphics graph = engine.getGraphics();
//        board.paintComponent(graph);
//    }

    /**
     * Test of piirraRuutu method, of class GameBoard.
     */
    @Test
    public void testPiirraRuutu() {
        System.out.println("piirraRuutu");
        int x = 3;
        int y = 4;
        Graphics graph = engine.getGraphics();
        board.piirraRuutu(x, y, RuutuTyyli.MatoBody, graph);
        assertEquals(Color.GREEN, graph.getColor());
    }

    /**
     * Test of tyhjennaBoard method, of class GameBoard.
     */
    @Test
    public void testTyhjennaBoard() {
        System.out.println("tyhjennaBoard");
        board.setRuutu(2, 3, RuutuTyyli.Hedelma);
        board.setRuutu(3, 6, RuutuTyyli.Hedelma);
        board.tyhjennaBoard();
        assertEquals(null, board.getTyyli(2, 3));
        assertEquals(null, board.getTyyli(3, 6));
    }

    /**
     * Test of taytaRuutu method, of class GameBoard.
     */
    @Test
    public void testTaytaRuutu_Point_RuutuTyyli() {
        System.out.println("taytaRuutu");
        Point piste = new Point(1, 1);
        RuutuTyyli tyyli = RuutuTyyli.MatoHead;        
        board.setRuutu(piste, tyyli);
        assertEquals(RuutuTyyli.MatoHead, board.getTyyli(1, 1));
        board.tyhjennaBoard();
    }

    /**
     * Test of taytaRuutu method, of class GameBoard.
     */
    @Test
    public void testTaytaRuutu_3args() {
        System.out.println("taytaRuutu");
        int x = 1;
        int y = 2;
        RuutuTyyli tyyli = RuutuTyyli.Hedelma;
        board.setRuutu(x, y, tyyli);
        assertEquals(RuutuTyyli.Hedelma, board.getTyyli(x, y));
        board.tyhjennaBoard();
    }

    /**
     * Test of getTyyli method, of class GameBoard.
     */
    @Test
    public void testGetTyyli() {
        System.out.println("getTyyli");
        RuutuTyyli expResult = null;
        RuutuTyyli expResult2 = RuutuTyyli.MatoBody;
        board.setRuutu(3, 9, RuutuTyyli.MatoBody);
        RuutuTyyli result = board.getTyyli(10, 11);
        RuutuTyyli result2 = board.getTyyli(3, 9);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }
    
    @Test
    public void testColor(){
        System.out.println("testaa asettuuko värit oikein ja samalla testaten onko GameBoard olemassa ja luotu oikein.");
        Color color = board.getBackground();
        assertEquals(Color.GRAY, color);
    }
}
