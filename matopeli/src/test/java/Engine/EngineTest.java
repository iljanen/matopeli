/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import junit.framework.TestCase;

/**
 *
 * @author Ilja
 */
public class EngineTest extends TestCase {
    
    public EngineTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Onko peli alussa uusi.
     */
    public void testOnkoUusiPeli() {
        System.out.println("startPeli");
        Engine instance = new Engine();
        assertFalse("Pelin pitäisi olla tägätty uudeksi peliksi pelin käynnistyessä",
                instance.onkoUusiPeli());
    }

    /**
     * Test of resetPeli method, of class Engine.
     */
    public void testResetPeli() {
        System.out.println("resetPeli");
        Engine instance = new Engine();
        instance.resetPeli();
        assertFalse("Uusipeli-tägi pitäisi olla false resetoinnin yhteydessä", instance.onkoUusiPeli());
        assertFalse("Hävinnyt-tägi pitäisi olla false resetoinnin yhteydessä", instance.onkoHavinnyt());
        assertEquals("Hedelmien pitäisi olla resetoinnit jälkeen 0 ja nyt oli "+instance.getHedelmat(),
                0, instance.getHedelmat());
        assertEquals("Pisteiden pitäisi olla resetoinnin jälkeen 0 ja nyt oli "+instance.getPisteet(),
                0, instance.getPisteet());
    }
}
