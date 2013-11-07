package Engine;

import Engine.GameBoard;
import UI.Snake;
import java.awt.*;
import javax.swing.*;

public class Engine {
    
    private static final int UPDATES_PER_SECOND = 12;
    private Canvas canvas;
    private GameBoard board;
    private Snake snake;
    
    public Engine(Canvas canvas){
        this.canvas = canvas;
        this.board = new GameBoard();
        this.snake = new Snake(board);
        snake.resetSnake();
    }
    
    public void start(){
        canvas.createBufferStrategy(2);       
        Graphics2D graphics = (Graphics2D)canvas.getBufferStrategy().getDrawGraphics();
        long start = 0L;
        long sleep = 0L;
        
        while(true){
            start = System.currentTimeMillis();
            update();
            render(graphics);
            canvas.getBufferStrategy().show();
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            sleep = (1000L/UPDATES_PER_SECOND)-(System.currentTimeMillis()-start);
            if(sleep > 0){
                try{
                    Thread.sleep(sleep);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void update(){
        snake.updateSnake();
    }
    
    private void render(Graphics2D graphics){
        board.draw(graphics);
    }
    
    
    
}
