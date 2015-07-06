import it.algos.algospref.Pref
import it.algos.algospref.Type
import it.algos.wikiapi.LibWiki

class VersioneBootStrap {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def versioneService

    //--metodo invocato direttamente da Grails
    //--tutte le aggiunte, modifiche e patch vengono inserite con una versione
    //--l'ordine di inserimento è FONDAMENTALE
    def init = { servletContext ->
        //--controllo del flusso
        log.debug 'init'

        //--prima installazione del programma
        if (versioneService && versioneService.installaVersione(1)) {
            versioneService.newVersione('Applicazione', 'Installazione iniziale')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(2)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.USA_CRONO_BIO
            pref.descrizione = 'Uso del download crono per le voci Bio'
            pref.type = Type.booleano
            pref.bool = false
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'USA_CRONO_BIO di default false')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(3)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.USA_LIMITE_DOWNLOAD
            pref.descrizione = 'Uso del limite di download per scaricare le voci Bio'
            pref.type = Type.booleano
            pref.bool = true
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'USA_LIMITE_DOWNLOAD di default true')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(4)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.MAX_DOWNLOAD
            pref.descrizione = 'Numero di voci in download ad ogni ciclo'
            pref.type = Type.intero
            pref.intero = 500
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'MAX_DOWNLOAD di default 500')
        }// fine del blocco if
    }// fine della closure

    //--metodo invocato direttamente da Grails
    def destroy = {
    }// fine della closure

}// fine della classe di tipo BootStrap
