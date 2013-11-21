package Engine;

import UI.GameBoard;
import UI.RuutuTyyli;
import UI.StatsBoard;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Logiikkaluokka, joka käsittelee lähes kaiken pelin logiikan
 * Luokka luo uuden GameBoardin käynnistyessään
 * @author Ilja
 */
public class Engine extends JFrame {
    
    private static long FRAME_TIME = 1000L / 50L;
    private static int MIN_MATO_PITUUS = 5;
    private static int MAX_SUUNNAT = 3;
    private Kello kello;
    private GameBoard board;
    private StatsBoard stats;
    private Random random;
    private boolean onkoUusiPeli;
    private boolean onkoHavinnyt;
    private boolean onkoPause;
    private LinkedList<Point> mato;
    private LinkedList<Suunta> suunnat;
    private int pisteet;
    private int hedelmatSyoty;
    private int seuraavanHedelmanPisteet;
    
    /**
     * Konstruktori testejä varten
     * Imitoi luokan varsinaista konsturktoria ja startPeli-metodia
     * @param mato Linkitetty lista-parametri madon sijaintia varten
     * @param suunnat Linkitetty lista- parametri madon liikkumissuuntaa varten
     */
    public Engine(LinkedList<Point> mato, LinkedList<Suunta> suunnat){
        this.mato = mato;       
        this.suunnat = suunnat;
        this.kello = new Kello(9.0f);
        this.board = new GameBoard(this);
        this.stats = new StatsBoard(this);
        this.onkoUusiPeli = true;
        this.onkoHavinnyt = false;
        this.pisteet = 0;
        this.hedelmatSyoty = 0;
        
    }
    
    /**
     * Varsinainen konstruktori Engine luokalle
     * alustaa pelin, luo GameBoard-olion
     * luo madonohjausbufferin 
     */
    public Engine(){
        super("Matopeli!");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        this.board = new GameBoard(this);
        this.stats = new StatsBoard(this);
        
        add(board, BorderLayout.CENTER);
        add(stats, BorderLayout.EAST);
        
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
                            kello.setPause(onkoPause);
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
    
    /**
     * metodi, joka alustaa pelin tarvitsemat parametrit ja siirtyy
     * loputtomaan looppiin, joka on vastuussa framen ja pelin päivityksestä
     */
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
            stats.repaint();
            
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
            pisteet += seuraavanHedelmanPisteet;
            uusiHedelma();
        }else if(tormays == RuutuTyyli.MatoBody){
            onkoHavinnyt = true;
            kello.setPause(true);
        }else if(seuraavanHedelmanPisteet > 10){
            seuraavanHedelmanPisteet--;
        }
    }
    
    /**
     * Ensin katsoo uusimman valuen suunnat-listalta ja päivittää
     * madon sijaintia sen mukaan.
     * Sen jälkeen tarkistaa, törmäsikö mato seinään.
     * Tämän jälkeen metodi päivittää madon sijainnin.
     * @return metodi palauttaa Ruututyylin, jolla madon pää päivityksen jälkeen on.
     */
    public RuutuTyyli paivitaMato(){
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
            Point hanta = mato.removeLast();
            board.setRuutu(hanta, null);
            edellinen = board.getTyyli(head.x, head.y);
        }
        
        if(edellinen != RuutuTyyli.MatoBody){
            board.setRuutu(mato.peekFirst(), RuutuTyyli.MatoBody);
            mato.push(head);
            board.setRuutu(head, RuutuTyyli.MatoHead);
            if(suunnat.size() > 1){
                suunnat.poll();
            }
        }
        return edellinen;
    }
    
    /**
     * metodi resetoi pelin ja boardin alkuasemaan.
     * listat tyhjennetään ja populoidaan alkuparametrien mukaan.
     * GameBoard-instanssi tyhjennetään, Kello-instanssi resetoidaan ja asetetaan
     * pelikentälle uuden hedelmän ja madon.
     */
    public void resetPeli(){
        this.onkoHavinnyt = false;
        this.onkoUusiPeli = false;
        
        this.pisteet = 0;
        this.hedelmatSyoty = 0;
        
        Point head = new Point(GameBoard.KOLUMNIT / 2, GameBoard.RIVIT / 2);
        
        if(mato.isEmpty()){
            mato.add(head);
        }else{
            mato.clear();
            mato.add(head);
        }
        
        
        
        board.tyhjennaBoard();
        board.setRuutu(head, RuutuTyyli.MatoHead);
        
        if(suunnat.isEmpty()){
            suunnat.add(Suunta.North);
        }else{
            suunnat.clear();
            suunnat.add(Suunta.North);
        }      
        
        kello.reset();
        
        uusiHedelma();
    }
    
    private void uusiHedelma(){
        
        this.seuraavanHedelmanPisteet = 100;
        
        int indeksi = random.nextInt(GameBoard.KOLUMNIT * GameBoard.RIVIT - mato.size());
        
        int tyhjaRuutuLoytyi = -1;
        
        for(int x = 0; x < GameBoard.KOLUMNIT; x++){
            for(int y = 0; y < GameBoard.RIVIT; y++){
                RuutuTyyli tyyli = board.getTyyli(x, y);
                if(tyyli == null || tyyli == RuutuTyyli.Hedelma){
                    if(++tyhjaRuutuLoytyi == indeksi){
                        board.setRuutu(x, y, RuutuTyyli.Hedelma);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * @return boolean-arvon OnkoUusiPeli.
     */
    public boolean onkoUusiPeli(){
        return onkoUusiPeli;
    }
    /**
     * @return boolean-arvon onkoHavinnyt.
     */
    public boolean onkoHavinnyt(){
        return onkoHavinnyt;
    }
    /**
     * @return boolean-arvon onkoPause.
     */
    public boolean onkoPause(){
        return onkoPause;
    }
    /**
     * @return pisteet-arvon.
     */
    public int getPisteet(){
        return pisteet;
    }
    /**
     * @return syötyjen hedelmien määrän.
     */
    public int getHedelmat(){
        return hedelmatSyoty;
    }
    /**
     * @return seuraavan hedelmän pisteiden määrä.
     */
    public int getSeuraavanHedelmanPisteet(){
        return seuraavanHedelmanPisteet;
    }
    /**
     * hakee suunnat-listasta ensimmäisen (pää)arvon.
     * @return listan ensimmäisen arvon.
     */
    public Suunta getSuunta(){
        return suunnat.peek();
    }
    /**
     * hakee mato-listasta ensimmäisen arvon ja palauttaa sen.
     * @return mato-listan ensimmäinen arvo.
     */
    public Point getSijainti(){
        return (mato.peekFirst());
    }
}    
    
