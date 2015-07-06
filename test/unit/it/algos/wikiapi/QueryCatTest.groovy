package it.algos.wikiapi

import org.junit.Test

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

/**
 * Created by gac on 03 lug 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
public class QueryCatTest {

    private static String TITOLO_CAT_BREVE = 'Eventi del 1902'
    private static String TITOLO_CAT_MEDIA = 'Nati nel 1420'
    private static String TITOLO_CAT_LUNGA = 'BioBot'

    // numero massimo di elementi restituiti dalle API
    //--500 utente normale
    //--5.000 bot
    private int limits

    @Test
    public void breve() {
        ArrayList<Integer> lista
        QueryCat categoria

        categoria = new QueryCat(TITOLO_CAT_BREVE)
        lista = categoria.getListaPageids()
        assertNotNull(lista)
        assertTrue(lista.size() == 2)
    }// end of single test

    @Test
    public void media() {
        ArrayList<Integer> lista
        QueryCat categoria

        categoria = new QueryCat(TITOLO_CAT_MEDIA)
        lista = categoria.getListaPageids()
        assertNotNull(lista)
        assertTrue(lista.size() == 33)
    }// end of single test

//    @Test
    public void lunga() {
        ArrayList<Integer> lista
        QueryCat categoria
        long fine
        long durata
        long durataSec
        long inizio = System.currentTimeMillis()

        categoria = new QueryCat(TITOLO_CAT_LUNGA)
        lista = categoria.getListaPageids()
        assertNotNull(lista)
        assertTrue(lista.size() > 280000)
        fine = System.currentTimeMillis()
        durata = fine - inizio
        durataSec = durata / 1000
        def stop
    }// end of single test

}// end of class
