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

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def mailService

    //--codifica dell'orario di attivazione
    //--MON, TUE, WED, THU, FRI, SAT, SUN

    private static String cronExpressionCiclo = "0 * * ? * *" //--H24/7 tutte le ore, tutti i giorni

    static triggers = {
        cron name: 'newBio', cronExpression: cronExpressionCiclo
    }// fine del metodo statico


    def execute() {
        String oggetto
        String testo = '\nCiclo newBio'
        String inizio = new Date().toString()
        String termine
        String mailTo = 'guidoceresa@me.com'
        String time = 'Report iniziato alle ' + inizio

        //--flag di attivazione
        if (Pref.getBool(LibWiki.USA_CRONO_BIO)) {

            if (apiService) {
                apiService.newBioCiclo()

                termine = new Date().toString()
                if (Pref.getBool(LibWiki.SEND_MAIL_INFO)) {
                    if (mailService) {
                        testo = time + ' e terminato alle ' + termine + testo
                        oggetto = 'Wikiapi'
                        mailService.sendMail {
                            to mailTo
                            subject oggetto
                            body testo
                        }
                    }
                }// fine del blocco if

            }// fine del blocco if

        }// fine del blocco if
    }// fine del metodo execute


} // end of Job Class
