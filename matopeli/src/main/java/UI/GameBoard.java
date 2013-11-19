package UI;

import Engine.Engine;
import java.awt.*;
import javax.swing.JPanel;

public class GameBoard extends JPanel {
    public static final int RIVIT = 25;
    public static final int KOLUMNIT = 25;
    public static final int RUUDUN_KOKO = 20;
    public static final int SILMAT_SIVUSTA = RUUDUN_KOKO / 6;
    public static final int SILMAT_YLHAALTA = RUUDUN_KOKO / 3;
    public static final int SILMAN_KOKO = RUUDUN_KOKO / 5;
    public static final Font font = new Font("Tahoma", Font.BOLD, 25);
    private Engine peli;
    private RuutuTyyli[] ruudut;
    public GameBoard(Engine engine){
        this.peli = engine;
        this.ruudut = new RuutuTyyli[RIVIT * KOLUMNIT];
        
        setPreferredSize(new Dimension(KOLUMNIT * RUUDUN_KOKO, RIVIT * RUUDUN_KOKO));
        setBackground(Color.DARK_GRAY);
    }
    
    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        
        for(int x = 0; x < KOLUMNIT; x++){
            for(int y = 0; y < RIVIT; y++){
                RuutuTyyli tyyli = getTyyli(x, y);
                if(tyyli != null){
                    piirraRuutu(x * RUUDUN_KOKO, y * RUUDUN_KOKO, tyyli, graph);
                }
            }
        }
        
        graph.setColor(Color.BLACK);
        graph.drawRect(0, 0, getWidth()-1, getHeight()-1);
        for(int x = 0; x < KOLUMNIT; x++){
            for(int y = 0; y < RIVIT; y++){
                graph.drawLine(x * RUUDUN_KOKO, 0, x * RUUDUN_KOKO, getHeight());
                graph.drawLine(0, y * RUUDUN_KOKO, getWidth(), y * RUUDUN_KOKO);
            }
        }
        
        if(peli.onkoHavinnyt()||peli.onkoUusiPeli()||peli.onkoPause()){
            graph.setColor(Color.WHITE);
            
            int KeskiX = getWidth()/2;
            int KeskiY = getHeight()/2;
            String isoViesti = null;
            String pieniViesti = null;
            
            if(peli.onkoUusiPeli()){
                isoViesti = "Matopeli!";
                pieniViesti = "Paina Enteriä aloittaaksesi!";
            }else if(peli.onkoHavinnyt()){
                isoViesti = "GameOver!";
                pieniViesti = "Paina Enteriä aloittaaksesi uuden pelin!";
            }else if(peli.onkoPause()){
                isoViesti = "Pause!";
                pieniViesti = "Paina P jatkaaksesi!";
            }
            graph.setFont(font);
            graph.drawString(isoViesti, KeskiX - graph.getFontMetrics().stringWidth(isoViesti) / 2, KeskiY - 50);
            graph.drawString(pieniViesti, KeskiX - graph.getFontMetrics().stringWidth(pieniViesti) / 2, KeskiY + 50);
        }
    }
    
    public void piirraRuutu(int x, int y, RuutuTyyli tyyli, Graphics graph){
        switch(tyyli){
            case Hedelma:
                graph.setColor(Color.ORANGE);
                graph.fillOval(x + 2, y + 2, RUUDUN_KOKO - 4, RUUDUN_KOKO - 4);
                break;
            case MatoBody:
                graph.setColor(Color.GREEN);
                graph.fillRect(x, y, RUUDUN_KOKO, RUUDUN_KOKO);
                break;
            case MatoHead:
                graph.setColor(Color.GREEN);
                graph.fillRect(x, y, RUUDUN_KOKO, RUUDUN_KOKO);
                
                graph.setColor(Color.BLACK);
                
                switch(peli.getSuunta()){
                    case North:{
                        int apuY = y + SILMAT_YLHAALTA;
                        graph.drawLine(x + SILMAT_SIVUSTA, apuY, x + SILMAT_SIVUSTA, apuY + SILMAN_KOKO);
                        graph.drawLine(x + RUUDUN_KOKO - SILMAT_SIVUSTA, apuY, x + RUUDUN_KOKO - SILMAT_SIVUSTA, 
                                apuY + SILMAN_KOKO);
                        break;
                    }    
                    case South:{
                        int apuY = y + RUUDUN_KOKO - SILMAT_YLHAALTA;
                        graph.drawLine(x + SILMAT_SIVUSTA, apuY, x + SILMAT_SIVUSTA, apuY - SILMAN_KOKO);
                        graph.drawLine(x + RUUDUN_KOKO - SILMAT_SIVUSTA, apuY, x + RUUDUN_KOKO - SILMAT_SIVUSTA, 
                                apuY - SILMAN_KOKO);
                        break;
                    }
                    case East:{
                        int apuX = x + RUUDUN_KOKO -SILMAT_YLHAALTA;
                        graph.drawLine(apuX, y + SILMAT_SIVUSTA, apuX - SILMAN_KOKO, y + SILMAT_SIVUSTA);
                        graph.drawLine(apuX, y + RUUDUN_KOKO - SILMAT_SIVUSTA, apuX - SILMAN_KOKO, 
                                y + RUUDUN_KOKO - SILMAT_SIVUSTA);
                        break;
                    }
                    case West:{
                        int apuX = x + SILMAT_YLHAALTA;
                        graph.drawLine(apuX, y + SILMAT_SIVUSTA, apuX + SILMAN_KOKO, y + SILMAT_SIVUSTA);
                        graph.drawLine(apuX, y + RUUDUN_KOKO - SILMAT_SIVUSTA, apuX + SILMAN_KOKO, 
                                y + RUUDUN_KOKO - SILMAT_SIVUSTA);
                    }
                }
        }
    }
    
    public void tyhjennaBoard(){
        for( int i = 0; i < ruudut.length; i++){
            ruudut[i] = null;
        }
    }
    
    public void setRuutu(Point piste, RuutuTyyli tyyli){
        setRuutu(piste.x, piste.y, tyyli);
    }
    
    public void setRuutu(int x, int y, RuutuTyyli tyyli){
        ruudut[y * RIVIT + x] = tyyli;
    }
    
    public RuutuTyyli getTyyli(int x, int y){
        return ruudut[y * RIVIT + x];
    }
    
    
}
