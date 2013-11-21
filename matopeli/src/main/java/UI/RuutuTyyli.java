package UI;

import java.awt.*;

/**
 * Enum-luokka, joka sisltää kaikki mahdolliset ruututyypit pelikentälle.
 * @author Ilja
 */
public enum RuutuTyyli {
    Hedelma("fruit.png"), 
    
    MatoHead, 
    
    MatoBody;
    
    private RuutuTyyli(){
        
    }
    
    private RuutuTyyli(String imageName){
        
    }
}
