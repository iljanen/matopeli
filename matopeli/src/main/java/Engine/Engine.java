package Engine;

import UI.GameBoard;
import UI.RuutuTyyli;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

public class Engine extends JFrame {
    
    private static long FRAME_TIME = 1000L / 50L;
    private static int MIN_MATO_PITUUS = 4;
    private static int MAX_SUUNNAT = 3;
    private Kello kello;
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
        
        add(board, BorderLayout.CENTER);
        
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
        this.kello = new Kello(9.0f);
        
        kello.setPause(true);
        
        while(true){
            long aloita = System.nanoTime();
            kello.update();
            
            if(kello.muutaKierrosMaara()){
                paivitaPeli();
            }
            
            board.repaint();
            
            long delta = (System.nanoTime() - aloita) / 1000000L;
            if(delta < FRAME_TIME){
                try{
                    Thread.sleep(FRAME_TIME - delta);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void paivitaPeli(){
        RuutuTyyli tormays = paivitaMato();
        
        if(tormays == RuutuTyyli.Hedelma){
            hedelmatSyoty++;
            pisteet ++;
            
        }else if(tormays == RuutuTyyli.MatoBody){
            onkoHavinnyt = true;
            kello.setPause(true);
        }
    }
    
    private RuutuTyyli paivitaMato(){
        Suunta suunta = suunnat.peekFirst();
        
        Point head = new Point(mato.peekFirst());
        switch(suunta){
            case North:
                head.y--;
                break;
            case East:
                head.x++;
                break;
            case West:
                head.x--;
                break;
            case South:
                head.y++;
                break;
        }
        
        if(head.x < 0 || head.x >= GameBoard.KOLUMNIT || head.y < 0 || head.y >= GameBoard.RIVIT){
            return RuutuTyyli.MatoBody;
        }
        
        RuutuTyyli edellinen = board.getTyyli(head.x, head.y); 
        if(edellinen != RuutuTyyli.Hedelma && mato.size() > MIN_MATO_PITUUS){
            Point hanta = mato.removeFirst();
            board.taytaRuutu(hanta, null);
            edellinen = board.getTyyli(head.x, head.y);
        }
        
        if(edellinen != RuutuTyyli.MatoBody){
            board.taytaRuutu(mato.peekFirst(), RuutuTyyli.MatoBody);
            mato.push(head);
            board.taytaRuutu(head, RuutuTyyli.MatoHead);
            if(suunnat.size() > 1){
                suunnat.poll();
            }
        }
        return edellinen;
    }
    
    public void resetPeli(){
        this.onkoHavinnyt = false;
        this.onkoUusiPeli = false;
        
        this.pisteet = 0;
        this.hedelmatSyoty = 0;
        
        Point head = new Point(GameBoard.KOLUMNIT / 2, GameBoard.RIVIT / 2);
        
        //mato.clear();
        //mato.add(head);
        
        board.tyhjennaBoard();
        board.taytaRuutu(head, RuutuTyyli.MatoHead);
        
        //suunnat.clear();
        //suunnat.add(Suunta.North);
        
        //kello.reset();
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
    public Point getSijainti(){
        //return (mato.peekFirst());
        return new Point(GameBoard.KOLUMNIT/2, GameBoard.RIVIT/2); // toistaiseksi käytössä testien suunnittelua varten.
    }
}    
    
