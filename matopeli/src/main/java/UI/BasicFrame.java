package UI;

import Engine.Engine;
import Engine.GameBoard;
import javax.swing.*;
import java.awt.*;

    
public class BasicFrame extends JFrame {
    
    public BasicFrame(String nimi){
        setTitle(nimi);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setPreferredSize(new Dimension(GameBoard.MAP_SIZE * GameBoard.TILE_SIZE,
                GameBoard.MAP_SIZE * GameBoard.TILE_SIZE));
        
        add(canvas);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        Engine engine = new Engine(canvas);
        engine.start();
    }
    
}
