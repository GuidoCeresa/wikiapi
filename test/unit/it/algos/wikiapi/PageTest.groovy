package it.algos.wikiapi

import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by gac on 28 giu 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
//@TestFor(Page)
public class PageTest {

    // utilizzo di un service con la businessLogic
    // il service NON viene iniettato automaticamente (perché siamo nel test)
    private ApiService apiService = new ApiService()

    private static String TITOLO = 'Nicola Conte (ufficiale)'
    private static int PAGEID = 698528
    private static int PAGEID_UTF8 = 2286987

    private LinkedHashMap mappaTxt
    private LinkedHashMap mappaObj

    // alcuni parametri utilizzati
    private Page pagina
    private String previsto = "";
    private String ottenuto = "";
    private int numPrevisto = 0;
    private int numOttenuto = 0;

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param title della pagina wiki
     * @return pagina grails costruita
     */
    public void creaPagina() {
        pagina = apiService.leggePagina(TITOLO)
        assertTrue(pagina.isValida())

        mappaTxt = pagina.getMappaTxt()
        isMappaStringheValida(mappaTxt)

        mappaObj = pagina.getMappa()
        isMappaValoriValida(mappaObj)

        previsto = TITOLO
        ottenuto = pagina.getTitle()
        assertEquals(ottenuto, previsto)

        numPrevisto = PAGEID
        numOttenuto = pagina.getPageid()
        assertEquals(numOttenuto, numPrevisto)

        ottenuto = pagina.getText()
        previsto = '{{F|militari italiani|luglio 2013}}'
        assertTrue(ottenuto.startsWith(previsto))
        previsto = '[[Categoria:Italo-libici|Conte, Nicola]]'
        assertTrue(ottenuto.endsWith(previsto))
    }// end of single test

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public void creaPagina2() {
        pagina = apiService.leggePagina(PAGEID)
        assertTrue(pagina.isValida())

        mappaTxt = pagina.getMappaTxt()
        isMappaStringheValida(mappaTxt)

        mappaObj = pagina.getMappa()
        isMappaValoriValida(mappaObj)

        previsto = TITOLO
        ottenuto = pagina.getTitle()
        assertEquals(ottenuto, previsto)

        numPrevisto = PAGEID
        numOttenuto = pagina.getPageid()
        assertEquals(numOttenuto, numPrevisto)

        ottenuto = pagina.getText()
        previsto = '{{F|militari italiani|luglio 2013}}'
        assertTrue(ottenuto.startsWith(previsto))
        previsto = '[[Categoria:Italo-libici|Conte, Nicola]]'
        assertTrue(ottenuto.endsWith(previsto))
    }// end of single test

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public void creaPagina23() {
        pagina = apiService.leggePagina(PAGEID_UTF8)
        assertTrue(pagina.isValida())

        mappaTxt = pagina.getMappaTxt()
        isMappaStringheValida(mappaTxt)

        mappaObj = pagina.getMappa()
        isMappaValoriValida(mappaObj)

        previsto = 'Mírzá `Abbás-i-Núrí'
        ottenuto = pagina.getTitle()
        assertEquals(ottenuto, previsto)
    }// end of single test

    /**
     * Controllo di validità della mappa di stringhe
     */
    private static void isMappaStringheValida(Map mappa) {
        String key
        Object value

        assertNotNull('Mappa non esistente', mappa)
        assertTrue('Mappa di lunghezza errata', mappa.size() == 19)

        //--parametri wiki base
        PagePar.getRead()?.each {
            key = it.toString()
            value = mappa."${key}"
            assertNotNull("Manca il campo ${key}", value)
        } // fine del ciclo each
    }// end of method

    /**
     * Controllo di validità della mappa di stringhe
     */
    private static void isMappaValoriValida(Map mappa) {
        String key
        Object value

        assertNotNull('Mappa non esistente', mappa)
        assertTrue('Mappa di lunghezza errata', mappa.size() == 19)

        //--parametri wiki base
        PagePar.getRead()?.each {
            isCampoTypoValido(mappa, it)
        } // fine del ciclo each
    }// end of method

    /**
     * Controllo di validità del singolo campo (field)
     *
     * @param field
     * @return status booleano
     */
    private static void isCampoTypoValido(Map mappa, PagePar field) {
        String key = field.toString()
        Object value = mappa."${key}"
        PagePar.TypeField type = field.getType()

        switch (type) {
            case PagePar.TypeField.string:
                assertNotNull("Manca il campo ${key}", value)
                break;
            case PagePar.TypeField.integerzero:
                assertTrue("Il campo ${key} non è un intero", value in Integer)
                break;
            case PagePar.TypeField.integernotzero:
                assertNotEquals("Il valore del campo ${key} non può essere uguale a zero", value, 0)
                assertTrue("Il campo ${key} non è un intero", value in Integer)
                break;
            case PagePar.TypeField.date:
                assertTrue("Il campo ${key} non è una data valida", value in Date)
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch
    }// end of method

}// end of testing class