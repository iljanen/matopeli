package UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Picture {
    
    private BufferedImage image;
    
    public Picture(String imageName){
        image = lataa(imageName);
    }
    
    private BufferedImage lataa(String imageName){
        BufferedImage pic = null;
        try{
            pic = ImageIO.read(new File("kuvat//"+imageName));
        }catch(IOException e){
            System.out.println("Image not found: "+imageName);
            pic = new BufferedImage(20,20,BufferedImage.TYPE_4BYTE_ABGR);
            Graphics graph = pic.createGraphics();
            graph.setColor(Color.ORANGE);
            graph.fillOval(32, 32, GameBoard.RUUDUN_KOKO, GameBoard.RUUDUN_KOKO);
        }
        return pic;
    }
    
    public void piirra(Graphics graph, int x, int y){
        graph.drawImage(image, x, y, null);
    }
}
