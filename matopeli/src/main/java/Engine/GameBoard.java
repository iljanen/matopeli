package Engine;

import java.awt.*;

public class GameBoard {
    public static final int TILE_SIZE = 25;
    public static final int MAP_SIZE = 20;
    private Tile[] tiles;
    
    public static enum Tile{
        MATO(Color.GREEN), HEDELMA(Color.ORANGE), TYHJA(null);
        
        private Color tileColor;
        
        private Tile(Color color){
            this.tileColor = color;
        }
        public Color getTileColor(){
            return tileColor;
        }
    }    
    
    public GameBoard(Engine engine){
        tiles = new Tile[MAP_SIZE*MAP_SIZE];
        resetBoard();
    }
    
    public void resetBoard(){
        for(int i = 0; i<tiles.length; i++){
            tiles[i]=Tile.TYHJA;
        }
    }
    
    public void setTile(int x, int y, Tile tile){
        tiles[y * MAP_SIZE + x] = tile;
    }
    
    public Tile getTile(int x, int y){
        return tiles[y * MAP_SIZE + x];
    }
    
    public void draw(Graphics2D graphic){
        graphic.setColor(Tile.MATO.getTileColor());
        
        for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++){
            int x = i % MAP_SIZE;
            int y = i / MAP_SIZE;
            
            if (tiles[i].equals(Tile.TYHJA)){
                continue;
            }
            if ( tiles[i].equals(Tile.HEDELMA)){
                graphic.setColor(Tile.HEDELMA.getTileColor());
                graphic.fillOval(x * TILE_SIZE + 4, y * TILE_SIZE + 4,
                        TILE_SIZE - 8, TILE_SIZE -8);
                graphic.setColor(Tile.MATO.getTileColor());
                graphic.fillRect(x * TILE_SIZE + 1, y * TILE_SIZE + 1,
                        TILE_SIZE - 2, TILE_SIZE - 2);
            }
        }
    }
}
