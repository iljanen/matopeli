package UI;

import java.awt.Graphics;

/**
* Enum-luokka, joka sisltää kaikki mahdolliset ruututyypit pelikentälle.
* @author Ilja
*/
public enum RuutuTyyli {
    Hedelma("fruit.png"),
    
    IsoHedelma,
    
    LyhentavaHedelma,
    
    MatoHead,
    
    MatoBody;
    
    private RuutuTyyli(){
        
    }
    
    private RuutuTyyli(String imageName){
        
    }

    void drawImage(Graphics graph, int x, int y) {
        Picture pic = null;
        if(this == Hedelma){
            pic = new Picture("fruit.png");
            pic.piirra(graph, x, y);
        }
        
    }
}