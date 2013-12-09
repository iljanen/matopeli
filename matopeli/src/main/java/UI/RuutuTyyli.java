package UI;

import java.awt.Graphics;

/**
* Enum-luokka, joka sisltää kaikki mahdolliset ruututyypit pelikentälle.
* @author Ilja
*/
public enum RuutuTyyli {
    Hedelma,
    
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
        if(this == IsoHedelma){
            pic = new Picture("red_fruit.png");
            pic.piirra(graph, x, y);
        }
        if(this == LyhentavaHedelma){
            pic = new Picture("blue_fruit.png");
            pic.piirra(graph, x, y);
        }
        
    }
}