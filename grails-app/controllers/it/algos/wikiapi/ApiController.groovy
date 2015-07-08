package it.algos.wikiapi

class ApiController {
    static boolean transactional = false

    public static String MANCA = 'Non è stata richiesta nessuna pagina'
    private static String ERRATO = 'La pagina indicata non esiste'
    public static String QUERY = 'Il typo di query indicata non esiste'

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def apiService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def mailService

    def index() {
        render 'Not visible'
    }// end of method

    /**
     * Parametri possibili (oltre ai 3 fissi di sistema):
     * title
     * id oppure pageid oppure pageids
     * type (pagina, voce, template)
     * nome oppure nomeTmp oppure template
     * richiesta (read, write)
     * text
     * summary
     */
    def query() {
        String testo = ''
        String titlePageid = ''
        TipoRicerca tipoQuery = null
        Type type
        String template
        TypoRequest richiesta = null
        String text
        String summary = ''

        if (params.size() < 4) {
            render MANCA
        }// fine del blocco if

        if (params.title) {
            titlePageid = params.title
            tipoQuery = TipoRicerca.title
        }// fine del blocco if

        if (params.id) {
            titlePageid = params.id
            tipoQuery = TipoRicerca.pageid
        }// fine del blocco if
        if (params.pageid) {
            titlePageid = params.pageid
            tipoQuery = TipoRicerca.pageid
        }// fine del blocco if
        if (params.pageids) {
            titlePageid = params.pageids
            tipoQuery = TipoRicerca.pageid
        }// fine del blocco if

        if (params.type) {
            type = params.type
        }// fine del blocco if

        if (params.nome) {
            template = params.nome
        }// fine del blocco if
        if (params.nomeTmp) {
            template = params.nomeTmp
        }// fine del blocco if
        if (params.template) {
            template = params.template
        }// fine del blocco if

        if (params.richiesta) {
            richiesta = params.richiesta
        }// fine del blocco if

        if (params.text) {
            text = params.text
        }// fine del blocco if

        if (params.summary) {
            summary = params.summary
        }// fine del blocco if

        if (!richiesta || richiesta == TypoRequest.read) {
            if (titlePageid && tipoQuery && type && template) {
                testo = apiService.legge(titlePageid, tipoQuery, type, template)
            } else {
                if (titlePageid && tipoQuery && type) {
                    testo = apiService.legge(titlePageid, tipoQuery, type)
                } else {
                    if (titlePageid && tipoQuery) {
                        testo = apiService.legge(titlePageid, tipoQuery)
                    } else {
                        if (titlePageid) {
                            testo = apiService.legge(titlePageid)
                        }// fine del blocco if-else
                    }// fine del blocco if-else
                }// fine del blocco if-else
            }// fine del blocco if-else
        } else {
            if (richiesta == TypoRequest.write && titlePageid && tipoQuery && text) {
                testo = apiService.scrive(titlePageid, tipoQuery, text, summary)
                testo = text
            } else {
                if (richiesta == TypoRequest.write && titlePageid && text) {
                    testo = apiService.scrive(titlePageid, text, summary)
                    testo = text
                }// fine del blocco if
            }// fine del blocco if-else
        }// fine del blocco if-else

        render testo
    }// end of method

    /**
     * Parametri possibili (oltre ai 3 fissi di sistema):
     * title
     */
    def download() {
        String title = ''

        if (params.size() < 4) {
            render MANCA
        }// fine del blocco if

        if (params.title) {
            title = params.title
        }// fine del blocco if

        if (title) {
            apiService.download(title)
        }// fine del blocco if

        render 'Registrata la pagina: ' + title
    }// end of method

    /**
     * Esegue un ciclo di controllo e creazione di nuovi records
     * Esegue 'al volo', indipendentemente dal flag USA_CRONO_BIO
     * Controlla il flag USA_LIMITE_DOWNLOAD
     * Usa il numero massimo (MAX_DOWNLOAD) di voci da scaricare ad ogni ciclo (se USA_LIMITE_DOWNLOAD=true)
     * Legge la categoria BioBot
     * Legge le voci WikiBio esistenti
     * Trova la differenza
     * Scarica MAX_DOWNLOAD voci dal server e crea MAX_DOWNLOAD nuovi records di WikiBio
     */
    def newBioCiclo() {
        if (apiService) {
            apiService.newBioCiclo(mailService)
        }// fine del blocco if

        render 'Eseguito un ciclo di controllo e creazione di nuovi records'
    }// end of method

}// end of controller class