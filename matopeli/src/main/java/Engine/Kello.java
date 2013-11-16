
package Engine;


public class Kello {
    
    private float aikaKierros;
    private long viimeisinPaivitys;
    private int kierrostenMaara;
    private float aikaaSeuraavaanKierrokseen;
    private boolean onkoPause;
    
    public Kello(float kierroksetSekunnissa){
        setKierroksetSekunnissa(kierroksetSekunnissa);
        reset();
    }
    
    public void setKierroksetSekunnissa(float kierroksetSekunnissa){
        this.aikaKierros = (1.0f /kierroksetSekunnissa) * 1000;
    }
    
    public void reset(){
        this.kierrostenMaara = 0;
        this.aikaaSeuraavaanKierrokseen = 0.0f;
        this.viimeisinPaivitys = getAika();
        this.onkoPause = false;
    }
    
    public void update(){
        long tamaPaivitys = getAika();
        float delta = (float)(tamaPaivitys - viimeisinPaivitys)+aikaaSeuraavaanKierrokseen;
        
        if(!onkoPause){
            this.kierrostenMaara+= (int)Math.floor(delta/aikaKierros);
            this.aikaaSeuraavaanKierrokseen = delta % aikaKierros;
        }
        this.viimeisinPaivitys = tamaPaivitys;
    }
    
    private static final long getAika(){
        return (System.nanoTime()/1000000L);
    }
    
    public void setPause(boolean pause){
        this.onkoPause = pause;
    }
    
    public boolean onkoPause(){
        return onkoPause;
    }
    
    public boolean muutaKierrosMaara(){
        if(kierrostenMaara > 0){
            this.kierrostenMaara--;
            return true;
        }
        return false;
    }
    
    public boolean katsoKierrosMaara(){
        return (kierrostenMaara > 0);
    }
}
