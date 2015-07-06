package it.algos.wikiapi

import groovy.json.JsonSlurper
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by gac on 27 giu 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
public class ApiServiceTest {

    // utilizzo di un service con la businessLogic
    // il service NON viene iniettato automaticamente (perché siamo nel test)
    private ApiService apiService = new ApiService()

    private Page pagina
    private String contenuto
    private String contenutoJSON

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param title della pagina wiki
     * @return pagina grails costruita
     */
    public void leggePaginaTitle() {
        pagina = apiService.leggePaginaTitle('Piozzano')
        assertTrue(pagina.isValida())
    }// end of single test

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public void leggePaginaPageid() {
        pagina = apiService.leggePaginaPageid(2741616)
        assertTrue(pagina.isValida())
    }// end of single test

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param title della pagina wiki
     * @return pagina grails costruita
     */
    public void leggePagina() {
        pagina = apiService.leggePagina('Piozzano')
        assertTrue(pagina.isValida())
    }// end of single test

    @Test
    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public void leggePagina2() {
        pagina = apiService.leggePagina(2741616)
        assertTrue(pagina.isValida())
    }// end of single test

    @Test
    /**
     * Legge il risultato di una pagina
     *
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return pagina grails costruita
     */
    public void leggePagina3() {
        pagina = apiService.leggePagina('Piozzano', TipoRicerca.title)
        assertTrue(pagina.isValida())

        pagina = apiService.leggePagina('2741616', TipoRicerca.pageid)
        assertTrue(pagina.isValida())

        pagina = apiService.leggePagina(2741616, TipoRicerca.pageid)
        assertTrue(pagina.isValida())

        pagina = apiService.leggePagina('Piozzano', TipoRicerca.pageid)
        assertNull(pagina)

        pagina = apiService.leggePagina('2741616', TipoRicerca.title)
        assertFalse('Pagina non trovata', pagina.isValida())
    }// end of single test

    @Test
    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param title della pagina wiki
     * @return risultato (solo testo) della voce
     */
    public void leggeVoce() {
        contenuto = apiService.leggeVoce('Piozzano')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
    }// end of single test


    @Test
    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param pageid della pagina wiki
     * @return risultato (solo testo) della voce
     */
    public void leggeVoce2() {
        contenuto = apiService.leggeVoce(2741616)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
    }// end of single test


    @Test
    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return risultato (solo testo) della voce
     */
    public void leggeVoce3() {
        contenuto = apiService.leggeVoce('Piozzano', TipoRicerca.title)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('[[Categoria:Piozzano| ]]'))

        contenuto = apiService.leggeVoce('2741616', TipoRicerca.pageid)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('[[Categoria:Piozzano| ]]'))

        contenuto = apiService.leggeVoce(2741616, TipoRicerca.pageid)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('[[Categoria:Piozzano| ]]'))

        contenuto = apiService.leggeVoce('Piozzano', TipoRicerca.pageid)
        assertEquals('Contenuto non previsto', contenuto, '')

        contenuto = apiService.leggeVoce('2741616', TipoRicerca.title)
        assertNull('Contenuto non previsto', contenuto)
    }// end of single test


    @Test
    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param title della pagina wiki
     * @param tag del template
     * @return risultato del template
     */
    public void leggeTmpl() {
        contenuto = apiService.leggeTmpl('Piozzano', 'Divisione')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))

        contenuto = apiService.leggeTmpl('Piozzano', 'Div')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))
    }// end of single test

    @Test
    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param pageid della pagina wiki
     * @param tag del template
     * @return risultato del template
     */
    public void leggeTmpl2() {
        contenuto = apiService.leggeTmpl(2741616, 'Divisione')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))
    }// end of single test

    @Test
    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return risultato del template
     */
    public void leggeTmpl3() {
        contenuto = apiService.leggeTmpl('Piozzano', TipoRicerca.title, 'Divisione')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))

        contenuto = apiService.leggeTmpl(2741616, TipoRicerca.pageid, 'Divisione')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))

        contenuto = apiService.leggeTmpl(2741616, TipoRicerca.pageid, 'Divisione')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Divisione'))
        assertTrue(contenuto.endsWith('http://www.comune.piozzano.pc.it\n}}'))

        contenuto = apiService.leggeTmpl('Piozzano', TipoRicerca.pageid, 'Divisione')
        assertEquals('Contenuto non previsto', contenuto, '')

        contenuto = apiService.leggeTmpl('2741616', TipoRicerca.title, 'Divisione')
        assertEquals('Contenuto non previsto', contenuto, '')
    }// end of single test


    @Test
    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param title della pagina wiki
     * @return risultato del template bio
     */
    public void leggeTmplBio() {
        contenuto = apiService.leggeTmplBio('Nicola Conte (ufficiale)')
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Bio'))
        assertTrue(contenuto.endsWith('italiano\n}}'))
    }// end of single test

    @Test
    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param pageid della pagina wiki
     * @return risultato del template bio
     */
    public void leggeTmplBio2() {
        contenuto = apiService.leggeTmplBio(698528)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Bio'))
        assertTrue(contenuto.endsWith('italiano\n}}'))
    }// end of single test

    @Test
    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param titlePageid (title oppure pageid)
     * @return risultato del template bio
     */
    public void leggeTmplBio3() {
        contenuto = apiService.leggeTmplBio('Nicola Conte (ufficiale)', TipoRicerca.title)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Bio'))
        assertTrue(contenuto.endsWith('italiano\n}}'))

        contenuto = apiService.leggeTmplBio(698528, TipoRicerca.pageid)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Bio'))
        assertTrue(contenuto.endsWith('italiano\n}}'))

        contenuto = apiService.leggeTmplBio(698528, TipoRicerca.pageid)
        assertNotNull('Manca il risultato', contenuto)
        assertTrue(contenuto.startsWith('{{Bio'))
        assertTrue(contenuto.endsWith('italiano\n}}'))

        contenuto = apiService.leggeTmplBio('Nicola Conte (ufficiale)', TipoRicerca.pageid)
        assertEquals('Contenuto non previsto', contenuto, '')

        contenuto = apiService.leggeTmplBio('698528', TipoRicerca.title)
        assertEquals('Contenuto non previsto', contenuto, '')
    }// end of single test

    @Test
    public void parseText() {
        def result
        contenutoJSON = '{"pageid":"2741616","ns":"0","title":"Piozzano","contentmodel":"wikitext","pagelanguage":"it","touched":"2015-06-26T14:49:16Z","lastrevid":"73478643","length":"8775","revid":"73478643","parentid":"72862985","user":"FeltriaUrbsPicta","userid":"322321","timestamp":"2015-06-23T23:38:43Z","size":"8775","comment":"standardizzo e correggo","contentformat":"text/x-wiki","csrftoken":"null","starttimestamp":"null"}'
        JsonSlurper slurper = new JsonSlurper()
        result = slurper.parseText(contenutoJSON)
        assertNotNull('Risposta JSON è nulla', result)
    }// end of single test


    @Test
    /**
     * Legge dal server wiki
     * <p>
     * Rimanda al metodo completo
     * Di default suppone il title
     * Di default suppone la Pagina come ritorno
     *
     * @param title della pagina wiki
     * @return risultato della pagina (JSON)
     */
    public void legge() {
        def result

        contenutoJSON = apiService.legge('Piozzano')

        JsonSlurper slurper = new JsonSlurper()
        result = slurper.parseText(contenutoJSON)
        assertNotNull('Risposta JSON è nulla', result)

        pagina = new Page(contenutoJSON)
        assertTrue(pagina.isValida())
    }// end of single test

    @Test
    /**
     * Controlla che il flag USA_CRONO_BIO sia attivo
     * Controlla il flag USA_LIMITE_DOWNLOAD
     * Usa il numero massimo (MAX_DOWNLOAD) di voci da scaricare ad ogni ciclo (se USA_LIMITE_DOWNLOAD=true)
     * Legge la categoria BioBot
     * Legge le voci WikiBio esistenti
     * Trova la differenza
     * Scarica MAX_DOWNLOAD voci dal server e crea MAX_DOWNLOAD nuovi records di WikiBio
     */
    public void newBioCiclo() {
        apiService.newBioCiclo()
    }// end of single test

//    /**
//     * Controllo di validità della pagina
//     *
//     * @param pagina
//     * @return status booleano
//     */
//    private static void isPaginaValidaLettura(Page pagina) {
//        isPaginaValida(pagina)
//        assertNull("Arrivato l'edittoken NON richiesto", pagina.getCsrftoken())
//    }// end of method

//    /**
//     * Controllo di validità della pagina
//     *
//     * @param pagina
//     * @return status booleano
//     */
//    private static void isPaginaValidaScrittura(Page pagina) {
//        isPaginaValida(pagina)
//        isCampoValido(pagina, PagePar.csrftoken)
//    }// end of method

//    /**
//     * Controllo di validità della mappa di stringhe
//     */
//    private static void isMappaStringheValida(Map mappa) {
//        String key
//        Object value
//
//        assertNotNull('Mappa non esistente', mappa)
//        assertTrue('Mappa di lunghezza errata', mappa.size() == 19)
//
//        //--parametri wiki base
//        PagePar.getRead()?.each {
//            key = it.toString()
//            value = mappa."${key}"
//            assertNotNull("Manca il campo ${key}", value)
//        } // fine del ciclo each
//    }// end of method

//    /**
//     * Controllo di validità della mappa di stringhe
//     */
//    private static void isMappaValoriValida(Map mappa) {
//        String key
//        Object value
//
//        assertNotNull('Mappa non esistente', mappa)
//        assertTrue('Mappa di lunghezza errata', mappa.size() == 19)
//
//        //--parametri wiki base
//        PagePar.getRead()?.each {
//            isCampoTypoValido(mappa, it)
//        } // fine del ciclo each
//    }// end of method

//    /**
//     * Controllo di validità del singolo campo (field)
//     *
//     * @param field
//     * @return status booleano
//     */
//    private static void isCampoTypoValido(Map mappa, PagePar field) {
//        String key = field.toString()
//        Object value = mappa."${key}"
//        PagePar.TypeField type = field.getType()
//
//        switch (type) {
//            case PagePar.TypeField.string:
//                assertNotNull("Manca il campo ${key}", value)
//                break;
//            case PagePar.TypeField.integerzero:
//                assertTrue("Il campo ${key} non è un intero", value in Integer)
//                break;
//            case PagePar.TypeField.integernotzero:
//                assertNotEquals("Il valore del campo ${key} non può essere uguale a zero", value, 0)
//                assertTrue("Il campo ${key} non è un intero", value in Integer)
//                break;
//            case PagePar.TypeField.date:
//                assertTrue("Il campo ${key} non è una data valida", value in Date)
//                break;
//            default: // caso non definito
//                break;
//        } // fine del blocco switch
//    }// end of method

}// end of testing class
