
package Engine;


/**
 * Kello-luokka ottaa laskee aikakierroksia ja
 * antaa tiedot Engine luokalle framien päivitystä varten.
 * @author Ilja
 */
public class Kello {
    
    private float aikaKierros;
    private long viimeisinPaivitys;
    private int kierrostenMaara;
    private float aikaaSeuraavaanKierrokseen;
    private boolean onkoPause;
    
    /**
     * Kello-luokan konstruktori.
     * Luo uuden kellon ja asettaa kierrosmäärän per sekunti.
     * @param kierroksetSekunnissa kierrosten määrä per sekunti.
     */
    public Kello(float kierroksetSekunnissa){
        setKierroksetSekunnissa(kierroksetSekunnissa);
        reset();
    }
    
    /**
     * setteri kierroksille sekunnissa.
     * @param kierroksetSekunnissa laskee ja palauttaa kierrosten määrän
     * sekunnissa saadun parametrin mukaan.
     */
    public void setKierroksetSekunnissa(float kierroksetSekunnissa){
        this.aikaKierros = (1.0f /kierroksetSekunnissa) * 1000;
    }
    
    /**
     * resetoi kellon asetukset ja muuttujat.
     * nollaa kierrosten määrän ja ajan seuraavaan kierrokseen ja asettaa
     * pause-muuttujan falseksi.
     * asettaa myös viimeisimmän päivityksen ajankohdaksi reaaliaikaan.
     */
    public void reset(){
        this.kierrostenMaara = 0;
        this.aikaaSeuraavaanKierrokseen = 0.0f;
        this.viimeisinPaivitys = getAika();
        this.onkoPause = false;
    }
    
    /**
     * päivittää kellon muuttujat.
     * päivittää vain jos pause-muuttuja on false, eli peli ei ole taukotilassa.
     * metodi kutsutaan joka framessa, jopa kun peli on tauko-tilassa.
     */
    public void update(){
        long tamaPaivitys = getAika();
        float delta = (float)(tamaPaivitys - viimeisinPaivitys)+aikaaSeuraavaanKierrokseen;
        
        if(!onkoPause){
            this.kierrostenMaara+= (int)Math.floor(delta/aikaKierros);
            this.aikaaSeuraavaanKierrokseen = delta % aikaKierros;
        }
        this.viimeisinPaivitys = tamaPaivitys;
    }
    
    private static long getAika(){
        return (System.nanoTime()/1000000L);
    }
    
    public void setPause(boolean pause){
        this.onkoPause = pause;
    }
    
    public boolean getOnkoPause(){
        return onkoPause;
    }
    
    /**
     * Jos kierros on kulunut tällä kellolla, kierrosten määrä vähenee yhdellä. 
     * @return true, jos kierros on kulunut, false, jos ei ole.
     */
    public boolean muutaKierrosMaara(){
        if(kierrostenMaara > 0){
            this.kierrostenMaara--;
            return true;
        }
        return false;
    }
    
    /**
     * sama kuin muutaKierrosMaara(), mutta ei tee mitään kierrosmäärälle eli on
     * pelkkä getteri.
     * @return true, jos kierros on kulunut, false jos ei ole.
     */
    public boolean katsoKierrosMaara(){
        return (kierrostenMaara > 0);
    }
}
