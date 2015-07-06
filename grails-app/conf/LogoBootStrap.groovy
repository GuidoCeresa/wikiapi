import it.algos.algoslogo.Evento
import it.algos.algoslogo.EventoService

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

    //--metodo invocato direttamente da Grails
    def init = { servletContext ->

        //--creazione dei records base della tavola Evento
        //--li crea SOLO se non esistono già
        Evento.findOrCreateByNome(EventoService.NUOVO).save(failOnError: true)
        Evento.findOrCreateByNome(EventoService.MODIFICA).save(failOnError: true)
        Evento.findOrCreateByNome(EventoService.CANCELLA).save(failOnError: true)
    }// fine della closure

    //--metodo invocato direttamente da Grails
    def destroy = {
    }// fine della closure

}// fine della classe di tipo BootStrap
