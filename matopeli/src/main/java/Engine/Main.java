package Engine;


/**
 * Main-metodi, joka käynnistää ohjelman.
 * @author Ilja
 */
public class Main {
    
    /**
     * Luo uuden Enginen, ja käynnistää pelin.
     * @param args
     */
    public static void main(String[] args) {
        
        Engine peli = new Engine();
        peli.startPeli();
    }
}
