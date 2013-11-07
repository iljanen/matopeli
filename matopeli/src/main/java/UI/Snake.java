package UI;

import Engine.GameBoard;
import Engine.GameBoard.Tile;
import java.awt.*;
import java.util.LinkedList;


public class Snake {
    
    private GameBoard board;
    private LinkedList<Point> points;
    private suunta current;
    private suunta temp;
    
    public Snake (GameBoard board){
        this.board = board;
        this.points = new LinkedList<Point>();
    }
    
    public static enum suunta{
        UP, DOWN, LEFT, RIGHT, NONE
    }
    
    public void resetSnake(){
        this.current = suunta.NONE;
        this.temp = suunta.NONE;
        
        Point head = new Point(GameBoard.MAP_SIZE / 2, GameBoard.MAP_SIZE / 2);
        points.clear();
        points.addFirst(head);
        board.setTile(head.x, head.y, Tile.MATO);
    }
    
    public Tile updateSnake(){
        this.current = temp;
        Point head = points.getFirst();
        switch(current){
            case UP: 
                if(head.y <= 0) {
                     return null;  
                }
                points.push(new Point(head.x, head.y -1));
                break;
                
            case DOWN:
                if(head.y >= GameBoard.MAP_SIZE -1){
                    return null;
                }
                points.push(new Point(head.x, head.y + 1));
                break;
                
            case LEFT:
                if(head.x <= 0){
                    return null;
                }
                points.push(new Point(head.x - 1, head.y));
                break;
                
            case RIGHT:
                if (head.x >= GameBoard.MAP_SIZE -1){
                    return null;
                }
                points.push(new Point(head.x + 1, head.y));
                break;
                
            case NONE:
                return Tile.TYHJA;
        }
        
        head = points.getFirst();
        
        Tile old = board.getTile(head.x, head.y);
        if (!old.equals(Tile.HEDELMA)){
            Point last = points.removeLast();
            board.setTile(last.x, last.y, Tile.TYHJA);
            old = board.getTile(head.x, head.y);
        }
        
        board.setTile(head.x, head.y, Tile.MATO);
        
        return old;
    }
}
