package UI;

import Engine.Engine;
import java.awt.*;
import javax.swing.JPanel;

public class StatsBoard extends JPanel{
    private static final Font ISO = new Font("Tahoma", Font.BOLD, 20);
    private static final Font KESKI = new Font("Tahoma", Font.BOLD, 16);
    private static final Font PIENI = new Font("Tahoma", Font.BOLD, 13);
    private Engine engine;
    
    public StatsBoard(Engine engine){
        this.engine = engine;
        
        setPreferredSize(new Dimension(300, GameBoard.RIVIT * GameBoard.RUUDUN_KOKO));
        setBackground(Color.BLACK);
    }
    
    private static final int STATISTIIKKA_ETAISYYS = 150;        
    private static final int OHJAUS_ETAISYYS = 320;    
    private static final int TEKSTI_ETAISYYS = 30;       
    private static final int PIENI_ETAISYYS = 30;       
    private static final int PITKÄ_ETAISYYS = 50;
    
    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        
        graph.setColor(Color.WHITE);
        graph.setFont(ISO);
        graph.drawString("Matopeli", getWidth()/2-graph.getFontMetrics().stringWidth("Matopeli")/2, 50);
        
        graph.setFont(KESKI);
        graph.drawString("Satistiikka", PIENI_ETAISYYS, STATISTIIKKA_ETAISYYS);
        graph.drawString("Ohjaus", PIENI_ETAISYYS, OHJAUS_ETAISYYS);
        
        graph.setFont(PIENI);
        
        int piirraY = STATISTIIKKA_ETAISYYS;
        graph.drawString("Pisteet: " + engine.getPisteet(), PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("Hedelmiä syöty: "+ engine.getHedelmat(), PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("Tämän hedelmän pisteet: "+ engine.getSeuraavanHedelmanPisteet(), PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        
        piirraY = OHJAUS_ETAISYYS;
        graph.drawString("YLÖS: W / nuolinäppäin", PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("ALAS: S / nuolinäppäin", PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("VASEN: A / nuolinäppäin", PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("OIKEA: D / nuolinäppäin", PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
        graph.drawString("TAUKO: P", PITKÄ_ETAISYYS, piirraY += TEKSTI_ETAISYYS);
    }
}
