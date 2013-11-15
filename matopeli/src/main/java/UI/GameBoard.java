package UI;

import Engine.Engine;
import java.awt.*;
import javax.swing.JPanel;

public class GameBoard extends JPanel {
    public static final int RIVIT = 25;
    public static final int KOLUMNIT = 25;
    public static final int RUUDUN_KOKO = 20;
    public static final Font font = new Font("Tahoma", Font.BOLD, 25);
    private Engine peli;
    private RuutuTyyli[] ruudut;
    public GameBoard(Engine engine){
        this.peli = engine;
        this.ruudut = new RuutuTyyli[RIVIT * KOLUMNIT];
        
        setPreferredSize(new Dimension(KOLUMNIT * RUUDUN_KOKO, RIVIT * RUUDUN_KOKO));
        setBackground(Color.DARK_GRAY);
    }
    
    public void tyhjennaBoard(){
        for( int i = 0; i < ruudut.length; i++){
            ruudut[i] = null;
        }
    }
    
    public void taytaRuutu(Point piste, RuutuTyyli tyyli){
        taytaRuutu(piste.x, piste.y, tyyli);
    }
    
    public void taytaRuutu(int x, int y, RuutuTyyli tyyli){
        ruudut[y * RIVIT + x] = tyyli;
    }
    
    public RuutuTyyli getTyyli(int x, int y){
        return ruudut[y * RIVIT + x];
    }
    
    
}
