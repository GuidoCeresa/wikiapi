package it.algos.wikiapi

import org.junit.Test
import it.algos.wikiapi.PagePar

import static org.junit.Assert.assertEquals

/**
 * Created by gac on 28 giu 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
public class PageParTest {

    // alcuni parametri utilizzati
    private ArrayList<PagePar> listaPrevista = null;
    private ArrayList<PagePar> listaOttenuta = null;
    private int numPrevisto = 0;
    private int numOttenuto = 0;


    @Test
    /**
     * Restituisce una collezione di tutti gli elementi
     *
     * @return collezione
     */
    public void getAll() {
        numPrevisto = 21
        listaOttenuta = PagePar.getAll()
        numOttenuto = listaOttenuta.size()
        assertEquals(numOttenuto, numPrevisto)
    }// end of single test

    @Test
    /**
     * Restituisce una collezione limitata agli elementi col flag info valido
     *
     * @return collezione
     */
    public void getInfos() {
        numPrevisto = 10
        listaOttenuta = PagePar.getInf()
        numOttenuto = listaOttenuta.size()
        assertEquals(numOttenuto, numPrevisto)
    }// end of single test



    @Test
    /**
     * Restituisce una collezione limitata agli elementi col flag info NON valido
     *
     * @return collezione
     */
    public void getRevisions() {
        numPrevisto = 9
        listaOttenuta = PagePar.getRev()
        numOttenuto = listaOttenuta.size()
        assertEquals(numOttenuto, numPrevisto)
    }// end of single test


    @Test
    /**
     * Restituisce una collezione degli elementi da restituire in lettura
     *
     * @return collezione
     */
    public void getJSONRead() {
        numPrevisto = 17
        listaOttenuta = PagePar.getRead()
        numOttenuto = listaOttenuta.size()
        assertEquals(numOttenuto, numPrevisto)
    }// end of single test

    @Test
    /**
     * Restituisce una collezione degli elementi da restituire in lettura e scrittura
     *
     * @return collezione
     */
    public void getJSONWrite() {
        numPrevisto = 19
        listaOttenuta = PagePar.getWrite()
        numOttenuto = listaOttenuta.size()
        assertEquals(numOttenuto, numPrevisto)
    }// end of single test

}// end of class
