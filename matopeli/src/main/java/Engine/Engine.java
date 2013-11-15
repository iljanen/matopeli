package Engine;

import UI.GameBoard;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class Engine extends JFrame {
    
    private static long FRAME_TIME = 1000L / 50L;
    private static int MIN_MATO_PITUUS = 4;
    private static int MAX_SUUNNAT = 3;
    private GameBoard board;
    private Random random;
    private boolean onkoUusiPeli;
    private boolean onkoHavinnyt;
    private boolean onkoPause;
    private LinkedList<Point> mato;
    private LinkedList<Suunta> suunnat;
    private int pisteet;
    private int hedelmatSyoty;
    
    public Engine(){
        super("Matopeli!");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        this.board = new GameBoard(this);
        
        //add(board, BorderLayout.CENTER);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        if(!onkoPause && !onkoHavinnyt){
                            if(suunnat.size() < MAX_SUUNNAT){
                                Suunta last = suunnat.peekLast();
                                if(last != Suunta.North &&  last != Suunta.South){
                                    suunnat.addLast(Suunta.North);
                                }
                            }
                        }
                        break;
                        
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        if(!onkoPause && !onkoHavinnyt){
                            if(suunnat.size() < MAX_SUUNNAT){
                                Suunta last = suunnat.peekLast();
                                if(last != Suunta.South && last != Suunta.North){
                                    suunnat.addLast(Suunta.South);
                                }
                            }
                        }
                        break;
                        
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if(!onkoPause && !onkoHavinnyt){
                            if(suunnat.size() < MAX_SUUNNAT){
                                Suunta last = suunnat.peekLast();
                                if(last != Suunta.East && last != Suunta.West){
                                    suunnat.addLast(Suunta.East);
                                }
                            }
                        }
                        break;
                        
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if(!onkoPause && !onkoHavinnyt){
                            if(suunnat.size() < MAX_SUUNNAT){
                                Suunta last = suunnat.peekLast();
                                if(last != Suunta.East && last != Suunta.West){
                                    suunnat.addLast(Suunta.West);
                                }
                            }
                        }
                        break;
                        
                    case KeyEvent.VK_P:
                        if(!onkoHavinnyt){
                            onkoPause = !onkoPause;
                        }
                        break;
                        
                    case KeyEvent.VK_ENTER:
                        if(onkoUusiPeli || onkoHavinnyt){
                            resetPeli();
                        }
                }
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void startPeli(){
        this.random = new Random();
        this.mato = new LinkedList<Point>();
        this.suunnat = new LinkedList<Suunta>();
        this.onkoUusiPeli = true;
        
        while(true){
            
        }
    }
    
    public void resetPeli(){
        this.onkoHavinnyt = false;
        this.onkoUusiPeli = false;
        
        this.pisteet = 0;
        this.hedelmatSyoty = 0;
        
        mato.clear();
        
        board.tyhjennaBoard();
        
        suunnat.clear();
        suunnat.add(Suunta.North);
    }
    
    public boolean onkoUusiPeli(){
        return onkoUusiPeli;
    }
    public boolean onkoHavinnyt(){
        return onkoHavinnyt;
    }
    public boolean onkoPause(){
        return onkoPause;
    }
    public int getPisteet(){
        return pisteet;
    }
    public int getHedelmat(){
        return hedelmatSyoty;
    }
    public Suunta getSuunta(){
        return suunnat.peek();
    }
}    
    
