package it.algos.wikiapi

import java.text.SimpleDateFormat

class Wiki {

    /** nomi interni dei campi (ordine non garantito) */
    //--parametri wiki base
    int pageid
    int ns
    String title

    //--parametri wiki info
    String contentmodel
    String pagelanguage
    Date touched
    int lastrevid
    int length

    //--parametri wiki revisions
    int revid
    int parentid
    String user
    int userid
    Date timestamp
    int size
    String comment
    String contentformat

    //--parametri wiki per la scrittura (second request)
    String csrftoken
    Date starttimestamp

    //--tempo di DOWNLOAD
    //--uso il formato Timestamp, per confrontarla col campo timestamp
    //--molto meglio che siano esattamente dello stesso tipo
    //--ultima lettura della voce effettuata dal programma Botbio
    //--momento in cui il record BioWiki è stato modificato in allineamento alla voce sul server wiki
    Date ultimaLettura = new Date()

    //--tempo di UPLOAD
    //--uso il formato Timestamp, per confrontarla col campo timestamp
    //--molto meglio che siano esattamente dello stesso tipo
    //--momento in cui la voce sul server wiki è stata modificata con il WrapBio costruito dal programma
    Date ultimaScrittura = LibWiki.DATA_NULLA

//    //--ridondante, costruito con il timestamp esatto della pagina sul server wiki
//    //--serve per visualizzare la data in forma ''breve'' più leggibile,
//    //--mentre rimane il valore esatto del campo originario timestamp
//    Date modificaWiki

//    //--ridondante, costruito con il ultimaLettura esatto della pagina
//    //--serve per visualizzare la data in forma ''breve'' più leggibile,
//    //--mentre rimane il valore esatto del campo originario timestamp
//    Date letturaWiki

//    //--ridondante, costruito con il tag wikipedia + il title della pagina
//    //--serve per visualizzare un campo extra di link a wikipedia, senza modificare il ''list'' standard
//    String wikiUrl

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui,
     * viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        pageid(unique: true)
        ns()
        title(unique: true, nullable: false, blank: false)

        contentmodel(nullable: false, blank: false)
        pagelanguage(nullable: false, blank: false)
        touched(nullable: false, formatoData: new SimpleDateFormat('d MMM yy'))
        lastrevid(nullable: false)
        length(nullable: false)

        revid(nullable: false)
        parentid(nullable: false)
        user(nullable: false, blank: false)
        userid(nullable: false)
        timestamp(nullable: false, formatoData: new SimpleDateFormat('d MMM yy'))
        size(nullable: false)
        comment(nullable: true, blank: true)
        contentformat(nullable: false, blank: true)

        csrftoken(nullable: true, blank: true)
        starttimestamp(nullable: true, formatoData: new SimpleDateFormat('d MMM yy'))

        ultimaLettura(nullable: false, formatoData: new SimpleDateFormat('d MMM yy'))
        ultimaScrittura(nullable: false, formatoData: new SimpleDateFormat('d MMM yy'))
    } // end of static constraints

    /**
     * Grails support two tipe of Inheritance:
     * 1) table-per-hierarchy mapping (default)
     * 2) table-per-subclasses
     * By default GORM classes use table-per-hierarchy inheritance mapping.
     * This has the disadvantage that columns cannot have a NOT-NULL constraint applied to them at the database level.
     * If you would prefer to use a table-per-subclass inheritance strategy you can do so as follows:
     *         tablePerHierarchy false
     * The mapping of the root Xyz class specifies that it will not be using table-per-hierarchy mapping for all child classes.
     */
    // nomi dei campi sul database, di default usa il nome interno del campo
    // la superclasse di ogni domainClass inserisce i campi dateCreated e lastUpdated
    // che vengono aggiornati automaticamente da GORM
    // per disabilitare l'automatismo, mettere a false la proprietà autoTimestamp nella classe specifica
    // Grails inserisce automaticamente la proprietà/campo 'versione' per l'optimistic locking
    // per disabilitare l'automatismo, mettere a false la proprietà version nella classe specifica
    static mapping = {
        tablePerHierarchy false

        // registrazione automatica delle date di creazione e update dei records
        // default del timestamping è true
        autoTimestamp true

        // numero di versione del singolo record per l'optimistic locking
        // default del versioning è true
        version true

        // stringa di lunghezza variabile
        comment type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return title
    } // end of toString

    /**
     * GORM supports the registration of events as methods that get fired
     * when certain events occurs such as deletes, inserts and updates
     * The following is a list of supported events:
     * beforeInsert - Executed before an object is initially persisted to the database
     * beforeUpdate - Executed before an object is updated
     * beforeDelete - Executed before an object is deleted
     * beforeValidate - Executed before an object is validated
     * afterInsert - Executed after an object is persisted to the database
     * afterUpdate - Executed after an object has been updated
     * afterDelete - Executed after an object has been deleted
     * onLoad - Executed when an object is loaded from the database
     */

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * prima di convalidare un record
     */
    def beforeValidate = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver creato un nuovo record
     */
    def afterInsert = {
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver registrato un record esistente
     */
    def afterUpdate = {
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver cancellato un record
     */
    def afterDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad

} // fine della domain classe
