import it.algos.algoslogo.Evento
import it.algos.algoslogo.EventoService
import it.algos.algoslogo.Logo
import it.algos.wikiapi.LibWiki

/**
 * Creato dal plugin AlgosLogo
 *
 * Classe di BootStrap in cui passa SEMPRE quando si lancia il programma
 * Passa anche in tutte le altre classi col suffisso -BootStrap
 *
 * Questa classe viene creata (solo se non esiste già) quando si installa il plugin
 * Viene cancellata quando si disinstalla il plugin
 * Non viene modificata quando si esegue un upgrade del plugin
 */
public class LogoBootStrap {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    //--metodo invocato direttamente da Grails
    def init = { servletContext ->
        Evento setupEvent
        Logo logoSetup

        //--creazione dei records base della tavola Evento
        //--li crea SOLO se non esistono già
        setupEvent = Evento.findOrCreateByNome(EventoService.SETUP).save(failOnError: true)
        Evento.findOrCreateByNome(EventoService.NUOVO).save(failOnError: true)
        Evento.findOrCreateByNome(EventoService.MODIFICA).save(failOnError: true)
        Evento.findOrCreateByNome(EventoService.CANCELLA).save(failOnError: true)
        Evento.findOrCreateByNome(LibWiki.NEW_BIO).save(failOnError: true)

        //--primo logo
        if (logoService && setupEvent) {
            logoSetup= Logo.findByEvento(setupEvent)
            if (!logoSetup) {
                logoService.setInfo(null, setupEvent, 'gac', '', 'Installazione iniziale del programma')
            }// fine del blocco if
        }// fine del blocco if

    }// fine della closure

    //--metodo invocato direttamente da Grails
    def destroy = {
    }// fine della closure

}// fine della classe di tipo BootStrap
