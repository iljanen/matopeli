package UI;

import javax.swing.*;
import java.awt.*;

    
public class BasicFrame extends JFrame {
    
    public BasicFrame(String nimi){
        setTitle(nimi);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.GRAY);
        canvas.setPreferredSize(new Dimension(500, 500));
        
        add(canvas);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
