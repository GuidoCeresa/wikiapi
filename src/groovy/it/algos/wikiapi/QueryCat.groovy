package it.algos.wikiapi

/**
 * Created by gac on 28 giu 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
public class QueryCat extends Query {

    //--stringa per la lista di categoria
    private static String CAT = '&list=categorymembers'

    //--stringa selezionare il namespace (0=principale - 14=sottocategorie) (per adesso solo il principale)
    private static String NS = '&cmnamespace=0'

    //--stringa selezionare il tipo di categoria (page, subcat, file) (per adesso solo page)
    private static String TYPE = '&cmtype=page'

    //--stringa per ottenere il codice di continuazione
    private static String CONT = '&rawcontinue'

    //--stringa per selezionare il numero di valori in risposta
    private static String LIMIT = '&cmlimit=500'

    //--stringa per indicare il titolo della pagina
    private static String TITLE = '&cmtitle=Category:'

    //--stringa iniziale (sempre valida) del DOMAIN a cui aggiungere le ulteriori specifiche
    private static String API_BASE_CAT = API_BASE + CAT + NS + TYPE + CONT + LIMIT + TITLE

    //--stringa per il successivo inizio della lista
    private static String CONTINUE = '&cmcontinue='

    // lista di pagine della categoria (namespace=0)
    private ArrayList<Integer> listaPageids

    /**
     * Costruttore completo
     */
    public QueryCat(String title) {
        super(title, TipoRicerca.title, TypoRequest.read)
    }// fine del metodo costruttore

    /**
     * Costruisce il domain per l'URL dal titolo
     *
     * @param titolo
     * @return domain
     */
    protected String getDomain() {
        String domain = ''
        String titolo
        String startDomain = API_BASE_CAT

        titolo = URLEncoder.encode(title, Cost.ENC)

        if (titolo) {
            domain = startDomain + titolo
        }// fine del blocco if

        if (continua) {
            domain += CONTINUE + continua
        }// fine del blocco if

        return domain
    } // fine del metodo

    /**
     * Informazioni, risultato e validita della risposta
     * Controllo del risultato (testo) ricevuto
     * Estrae i valori e costruisce una mappa
     *
     * Sovrascritto nelle sottoclassi
     */
    protected void regolaRisultato() {
        ArrayList<Integer> lista
        String txtContinua
        String risultatoRequest = this.getRisultato()

        lista = LibWiki.creaListaCatJson(risultatoRequest)
        if (lista) {
            this.addLista(lista)
        }// fine del blocco if

        txtContinua = LibWiki.creaCatContinue(risultatoRequest)
        this.continua = txtContinua
    } // fine del metodo

    void addLista(ArrayList<Integer> listaNew) {
        ArrayList<Integer> lista

        lista = this.getListaPageids()
        if (!lista) {
            lista = new ArrayList<Integer>()
        }// fine del blocco if

        lista = lista + listaNew
        this.setListaPageids(lista)
    } // fine del metodo


    void setListaPageids(ArrayList<Integer> listaPageids) {
        this.listaPageids = listaPageids
    } // fine del metodo

    public ArrayList<Integer> getListaPageids() {
        return listaPageids
    } // fine del metodo

    public String getTxtPageids() {
        return LibWiki.creaListaPageids(getListaPageids())
    } // fine del metodo


}// end of class
