package it.algos.wikiapi

import grails.util.Holders

/**
 * Superclasse per le Request al server MediaWiki
 * Fornisce le funzionalità di base
 * Necessita di Login per la sottoclasse QueryMultiId
 * Nelle sottoclassi vengono implementate le funzionalità specifiche
 */
public abstract class Query {

    //--language selezionato (per adesso solo questo)
    protected static String LANGUAGE = 'it'

    //--progetto selezionato (per adesso solo questo)
    protected static String PROJECT = 'wikipedia'

    //--stringa iniziale (sempre valida) del DOMAIN a cui aggiungere le ulteriori specifiche
    protected static String API_BASE = Cost.API_HTTP + LANGUAGE + Cost.API_WIKI + PROJECT + Cost.API_QUERY + Cost.API_FORMAT

    //--recupera la grailsApplication da Holders, perché nelle classi del path src/groovy
    //--NON viene iniettata automaticamente
    def grailsApplication = Holders.grailsApplication

    //--tipo di ricerca della pagina
    //--di default il titolo
    private TipoRicerca tipoRicerca = TipoRicerca.title

    //--tipo di request - solo una per leggere - due per scrivere
    //--di default solo lettura (per la scrittura serve il login)
    private TypoRequest tipoRequest = TypoRequest.read

    protected String title
    private String pageid

    // risultato della pagina
    // risultato grezzo della query nel formato prescelto
    protected String risultato

    // collegamento utilizzato
//    protected Login login = null

    //--token per la continuazione della query
    protected String continua = ''

    /**
     * Costruttore di default per il sistema (a volte serve)
     */
    public Query() {
    }// fine del metodo costruttore

    /**
     * Costruttore completo
     */
    public Query(String titlepageid, TipoRicerca tipoRicerca, TypoRequest tipoRequest) {
        this.tipoRicerca = tipoRicerca
        this.tipoRequest = tipoRequest
        this.inizializza(titlepageid)
    }// fine del metodo costruttore

    /**
     * Costruttore completo
     */
    public Query(int pageid, TipoRicerca tipoRicerca, TypoRequest tipoRequest) {
        this.tipoRicerca = tipoRicerca
        this.tipoRequest = tipoRequest
        this.inizializza(pageid.toString())
    }// fine del metodo costruttore

    protected void inizializza(String titlepageid) {
        String testo

        switch (tipoRicerca) {
            case TipoRicerca.title:
                title = titlepageid
                break;
            case TipoRicerca.pageid:
                pageid = titlepageid
                break;
            case TipoRicerca.listaPageids:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        firstRequest()
        while (!continua.equals('')) {
            firstRequest()
        } // fine del blocco while
        def step
    } // fine del metodo

    /**
     * This module only accepts POST requests.
     * Parameters first request:
     *      action  = query
     *      format  = json
     *      prop    = info|revision
     *      intoken = edit
     *      titles  = xxx
     * Return:
     *      "pageid": "22958",
     *      "ns": "2",
     *      "title": "Utente:Gac/Sandbox4",
     *      "contentmodel": "wikitext",
     *      "pagelanguage": "it",
     *      "touched": "2012-11-05T09:32:37Z",
     *      "lastrevid": 53714557,
     *      "counter": "",
     *      "length": 10,
     *      "starttimestamp": "2013-09-15T05:54:35Z",
     *      "edittoken": "c3c28fbdf02b792bbcd367377d6ed6d5+\\",
     *      "revid": 53714557,
     *      "parentid": 53714550,
     *      "minor": "",
     *      "user": "Gac",
     *      "timestamp": "2012-11-05T09:32:37Z",
     *      "comment": "test"
     */
    //--legge la pagina per ottenere il token
    //--recupera informazioni sulla pagina
    //--recupera il risultato della pagina
    private firstRequest() {
        // variabili e costanti locali di lavoro
        boolean continua = true
        String domain
        URLConnection connection;
        InputStream input = null
        InputStreamReader inputReader = null
        BufferedReader readBuffer = null
        StringBuffer textBuffer = new StringBuffer()
        String stringa

        // find the target
        domain = this.getDomain()
        connection = new URL(domain).openConnection()
        connection = regolaConnessione(connection)

        // regola l'entrata
        // regola l'entrata
        try { // prova ad eseguire il codice
            input = connection.getInputStream();
            inputReader = new InputStreamReader(input, 'UTF8');
        } catch (Exception unErrore) { // intercetta l'errore
            println('timeout')
            continua = false
        }// fine del blocco try-catch

        // legge la risposta
        if (continua) {
            readBuffer = new BufferedReader(inputReader)
            while ((stringa = readBuffer.readLine()) != null) {
                textBuffer.append(stringa)
            }// fine del blocco while
        }// fine del blocco if

        // chiude
        if (readBuffer) {
            readBuffer.close()
        }// fine del blocco if
        if (inputReader) {
            inputReader.close()
        }// fine del blocco if
        if (input) {
            input.close()
        }// fine del blocco if

        // valore di ritorno della request
        risultato = textBuffer.toString()
        this.regolaRisultato()
    } // fine del metodo

    //--Costruisce il domain per l'URL dal titolo della pagina o dal pageid (a seconda del costruttore usato)
    //--@return domain
    protected String getDomain() {
        String domain = ''
        String titolo
        String startDomain = API_BASE + Cost.CONTENT_ALL

        if (tipoRequest == TypoRequest.write) {
            startDomain += Cost.TOKEN
        }// fine del blocco if

        switch (tipoRicerca) {
            case TipoRicerca.title:
                titolo = URLEncoder.encode(title, Cost.ENC)
                domain = startDomain + Cost.TITLES + titolo
                break;
            case TipoRicerca.pageid:
                domain = startDomain + Cost.PAGEIDS + pageid
                break;
            case TipoRicerca.listaPageids:
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return domain
    } // fine del metodo

    /**
     * Regola i parametri della connessione
     * Recupera i cookies dal Login di registrazione
     *
     * @param urlConn connessione
     */
    protected static URLConnection regolaConnessione(URLConnection urlConn) {
        // variabili e costanti locali di lavoro
        String txtCookies

        urlConn.setDoOutput(true)

        // regolo i cookies e le property
        urlConn.setRequestProperty('Accept-Encoding', 'GZIP');
        urlConn.setRequestProperty('Content-Encoding', 'GZIP');
        urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        urlConn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Macintosh; U; PPC Mac OS X; it-it) AppleWebKit/418.9 (KHTML, like Gecko) Safari/419.3");

        // valore di ritorno
        return urlConn
    } // fine del metodo

    /**
     * Informazioni, risultato e validita della risposta
     * Controllo del risultato (testo) ricevuto
     * Estrae i valori e costruisce una mappa
     *
     * Sovrascritto nelle sottoclassi
     */
    protected void regolaRisultato() {
    } // fine del metodo

    protected boolean isNotMissing() {
        boolean esistevaPagina = true
        def valoreMissing
        HashMap mappa = this.getMappa()

        if (mappa) {
            valoreMissing = mappa['missing']
            if (valoreMissing != null && valoreMissing instanceof String) {
                esistevaPagina = false
            }// fine del blocco if
        }// fine del blocco if

        return esistevaPagina
    } // fine del metodo


    HashMap getMappa() {
        return mappa
    }

    void setMappa(HashMap mappa) {
        this.mappa = mappa
    }

    String getRisultato() {
        return risultato
    }

    String getContinua() {
        return continua
    }

} // fine della classe
