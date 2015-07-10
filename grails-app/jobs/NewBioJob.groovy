import it.algos.algospref.Pref
import it.algos.wikiapi.LibWiki

/**
 * Created by gac on 04 lug 2015.
 * Using specific Templates (Entity, Domain, Modulo)
 *
 * Esegue un ciclo di controllo e creazione di nuovi records
 * Controlla che il flag USA_CRONO_BIO sia attivo
 * Controlla il flag USA_LIMITE_DOWNLOAD
 * Usa il numero massimo (MAX_DOWNLOAD) di voci da scaricare ad ogni ciclo (se USA_LIMITE_DOWNLOAD=true)
 * Legge la categoria BioBot
 * Legge le voci WikiBio esistenti
 * Trova la differenza
 * Scarica MAX_DOWNLOAD voci dal server e crea MAX_DOWNLOAD nuovi records di WikiBio
 */
public class NewBioJob {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def apiService
    def mailService
    def logoService

    //--codifica dell'orario di attivazione
    //--MON, TUE, WED, THU, FRI, SAT, SUN

    private static String cronExpressionCiclo = "0 0 1/2 ? * *" //--H24/7 tutte le ore, tutti i giorni

    static triggers = {
        cron name: 'newBio', cronExpression: cronExpressionCiclo
    }// fine del metodo statico


    def execute() {
        //--flag di attivazione
        if (Pref.getBool(LibWiki.USA_CRONO_BIO)) {

            if (apiService) {
                apiService.newBioCiclo(mailService, logoService)
            }// fine del blocco if

        }// fine del blocco if
    }// fine del metodo execute


} // end of Job Class
