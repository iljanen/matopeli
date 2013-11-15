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
     * Test of startPeli method, of class Engine.
     */
    public void testStartPeli() {
        System.out.println("startPeli");
        Engine instance = new Engine();
        instance.startPeli();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPeli method, of class Engine.
     */
    public void testResetPeli() {
        System.out.println("resetPeli");
        Engine instance = new Engine();
        instance.resetPeli();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
