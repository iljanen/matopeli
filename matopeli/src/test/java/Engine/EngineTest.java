
package Engine;

import java.awt.event.KeyEvent;
import junit.framework.TestCase;

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
     * Test of keyPressed method, of class Engine.
     */

    /**
     * Test of start method, of class Engine.
     */
    public void testStart() {
        System.out.println("start");
        Engine instance = null;
        instance.startPeli();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
