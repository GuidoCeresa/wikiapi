import it.algos.algospref.Pref
import it.algos.algospref.Type
import it.algos.wikiapi.LibWiki

/**
 * Creato dal plugin AlgosVers
 *
 * Classe di BootStrap in cui passa SEMPRE quando si lancia il programma
 * Passa anche in tutte le altre classi col suffisso -BootStrap
 *
 * Questa classe viene creata (solo se non esiste già) quando si installa il plugin
 * NON viene cancellata quando si disinstalla il plugin
 * (per preservare il log delle versioni, anche senza lo specifico Controller)
 * Non viene modificata quando si esegue un upgrade del plugin
 */
public class VersioneBootStrap {

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
            versioneService.newVersione('Preferenze', 'MAX_DOWNLOAD di default 1000')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(5)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.NUM_RECORDS_INDEX_BIO
            pref.descrizione = 'Numero di records visualizzati un una schermata di WikiBio'
            pref.type = Type.intero
            pref.intero = 1000
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'NUM_RECORDS_INDEX_BIO di default 1000')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(6)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.SEND_MAIL_ERROR
            pref.descrizione = 'Spedisce una mail in caso di errore, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = true
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'SEND_MAIL_ERROR di default true')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(7)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.SEND_MAIL_WARN
            pref.descrizione = 'Spedisce una mail in caso di malfunzionamento, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = false
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'SEND_MAIL_WARN di default false')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(8)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.SEND_MAIL_INFO
            pref.descrizione = 'Spedisce una mail di informazioni, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = false
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'SEND_MAIL_INFO di default false')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(9)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.LOG_ERROR
            pref.descrizione = 'Registra un log in caso di errore, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = true
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'LOG_ERROR di default true')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(10)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.LOG_WARN
            pref.descrizione = 'Registra un log in caso di malfunzionamento, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = false
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'LOG_WARN di default false')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(11)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.LOG_INFO
            pref.descrizione = 'Registra un log di informazioni, nei punti in cui il codice lo prevede'
            pref.type = Type.booleano
            pref.bool = false
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'LOG_INFO di default false')
        }// fine del blocco if

        //--creata una nuova preferenza
        if (versioneService && versioneService.installaVersione(12)) {
            Pref pref = new Pref()
            pref.ordine = Pref.list().size() + 1
            pref.code = LibWiki.USA_FLASH_TRUE_DOWNLOAD
            pref.descrizione = 'Usa la registrazione immediata nel ciclo NewBioDownload'
            pref.type = Type.booleano
            pref.bool = true
            pref.save(flush: true)
            versioneService.newVersione('Preferenze', 'USA_FLASH_TRUE_DOWNLOAD di default true')
        }// fine del blocco if
    }// fine della closure

    //--metodo invocato direttamente da Grails
    def destroy = {
    }// fine della closure

}// fine della classe di tipo BootStrap
