
package Engine;

import UI.GameBoard;
import UI.RuutuTyyli;
import java.awt.Point;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilja
 */
public class EngineTest{
       
    Engine engine;
    LinkedList<Point> mato = new LinkedList<Point>();
    LinkedList<Suunta> suunnat = new LinkedList<Suunta>();
    
    @Before
            public void setup(){
                engine = new Engine(mato, suunnat);              
                Point head = new Point(GameBoard.KOLUMNIT/2, GameBoard.RIVIT/2);
                mato.add(head);                
                suunnat.add(Suunta.North);
            }
    /**
     * Onko peli alussa uusi.
     */
    @Test 
    public void testOnkoUusiPeli() {
        System.out.println("OnkoUusiPeli?");
        assertTrue("Pelin pitäisi olla tägätty uudeksi peliksi pelin käynnistyessä",
                engine.onkoUusiPeli());
    }

    /**
     * Resetoituuko kaikki?
     */
    @Test
    public void testResetPeli() {
        System.out.println("resetPeli");
        assertTrue("Uusipeli-tägi pitäisi olla true resetoinnin yhteydessä", engine.onkoUusiPeli());
        assertFalse("Hävinnyt-tägi pitäisi olla false resetoinnin yhteydessä", engine.onkoHavinnyt());
        assertEquals("Hedelmien pitäisi olla resetoinnit jälkeen 0 ja nyt oli "+engine.getHedelmat(),
                0, engine.getHedelmat());
        assertEquals("Pisteiden pitäisi olla resetoinnin jälkeen 0 ja nyt oli "+engine.getPisteet(),
                0, engine.getPisteet());
    }
    
    @Test
    public void testMadonPaaKeskella() {
        System.out.println("MadonPääKeskelläUudenPelinAlkaessa?");
        Point apu = new Point(GameBoard.KOLUMNIT /2, GameBoard.RIVIT / 2);
        assertEquals(apu, engine.getSijainti());
    }
            
    
    @Test
    public void haviaakoPelinKunTormaaSeinaan(){
        mato.clear();
        mato.add(new Point(-1, 26));
        System.out.println("Seinääntörmäys testi");
        assertEquals(RuutuTyyli.MatoBody, engine.paivitaMato());
    }
    
    
}
