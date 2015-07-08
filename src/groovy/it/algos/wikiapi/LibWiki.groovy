package it.algos.wikiapi

import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap

/**
 * Libreria
 */
public abstract class LibWiki {

    //--preferenza
    public static final String USA_CRONO_BIO = 'usaCroonoBio'
    public static final String USA_LIMITE_DOWNLOAD = 'usaLimiteDownload'
    public static final String MAX_DOWNLOAD = 'maxDownload'
    public static final String NUM_RECORDS_INDEX_BIO = 'numRecordsIndexBio'
    public static final String SEND_MAIL_ERROR = 'sendMailError'
    public static final String SEND_MAIL_WARN = 'sendMailWarn'
    public static final String SEND_MAIL_INFO = 'sendMailInfo'

    private static String QUERY = 'query'
    private static String PAGEID = 'pageid'
    private static String PAGES = 'pages'
    private static String REVISIONS = 'revisions'
    private static String CATEGORY_MEMBERS = 'categorymembers'
    private static String QUERY_CONTINUE = 'query-continue'

    public static final String REF = "<ref>";
    public static final String NOTE = "<!--"
    public static final String GRAFFA_INI = '{'
    public static final String GRAFFE_INI = GRAFFA_INI + GRAFFA_INI
    public static final String GRAFFA_END = '}'
    public static final String GRAFFE_END = GRAFFA_END + GRAFFA_END
    public static final String BIO = 'Bio'
    private static String VIR = ','
    private static String APICI = '"'
    private static String PUNTI = ':'

    /**
     * tag per la stringa vuota
     */
    public static final String VUOTA = ''

    /**
     * tag per il valore falso per una posizione
     */
    public static final int INT_NULLO = -1

    public static Date DATA_NULLA = new Date(70, 0, 1)

    /**
     * Restituisce il numero di occorrenze di un tag nel testo.
     * Il tag non viene trimmato ed è sensibile agli spazi prima e dopo
     *
     * @param testo da spazzolare
     * @param tag da cercare
     * @return numero di occorrenze
     *         zero se non ce ne sono
     */
    public static getNumTag(String testo, String tag) {
        int numTag = 0
        int pos

        // controllo di congruità
        if (testo && tag) {
            if (testo.contains(tag)) {
                pos = testo.indexOf(tag)
                while (pos != -1) {
                    pos = testo.indexOf(tag, pos + tag.length())
                    numTag++
                }// fine di while
            } else {
                numTag = 0
            }// fine del blocco if-else
        }// fine del blocco if

        return numTag
    } // fine del metodo

    /**
     * Restituisce il numero di occorrenze di una coppia di graffe iniziali nel testo.
     *
     * @param testo da spazzolare
     * @param graffe da cercare
     * @return numero di occorrenze
     *         zero se non ce ne sono
     */
    public static getNumGraffeIni(String testo) {
        return getNumTag(testo, GRAFFE_INI)
    } // fine del metodo

    /**
     * Restituisce il numero di occorrenze di una coppia di graffe finali nel testo.
     *
     * @param testo da spazzolare
     * @param graffe da cercare
     * @return numero di occorrenze
     *         zero se non ce ne sono
     */
    public static getNumGraffeEnd(String testo) {
        return getNumTag(testo, GRAFFE_END)
    } // fine del metodo

    /**
     * Controlla che le occorrenze del tag iniziale e di quello finale si pareggino all'interno del testo.
     * Ordine ed annidamento NON considerato
     *
     * @param testo da spazzolare
     * @param tagIni tag iniziale
     * @param tagEnd tag finale
     * @return vero se il numero di tagIni è uguale al numero di tagEnd
     */
    public static boolean isPariTag(String testo, String tagIni, String tagEnd) {
        boolean pari = false
        int numIni
        int numEnd

        // controllo di congruità
        if (testo && tagIni && tagEnd) {
            numIni = getNumTag(testo, tagIni)
            numEnd = getNumTag(testo, tagEnd)
            pari = (numIni == numEnd)
        }// fine del blocco if

        return pari
    } // fine del metodo

    /**
     * Controlla che le occorrenze delle graffe iniziali e finali si pareggino all'interno del testo.
     * Ordine ed annidamento NON considerato
     *
     * @param testo da spazzolare
     * @return vero se il numero di GRAFFE_INI è uguale al numero di GRAFFE_END
     */
    public static boolean isPariGraffe(String testo) {
        return isPariTag(testo, GRAFFE_INI, GRAFFE_END)
    } // fine del metodo

    /**
     * Elimina la testa iniziale della stringa, se esiste. <br>
     * <p>
     * Esegue solo se la stringa è valida. <br>
     * Se manca la testa, restituisce la stringa. <br>
     * Elimina spazi vuoti iniziali e finali. <br>
     *
     * @param entrata stringa in ingresso
     * @param testa da eliminare
     * @return uscita stringa convertita
     */
    public static String levaTesta(String entrata, String testa) {
        String uscita = entrata;

        if (entrata != null) {
            uscita = entrata.trim();
            if (testa != null) {
                testa = testa.trim();
                if (uscita.startsWith(testa)) {
                    uscita = uscita.substring(testa.length());
                    uscita = uscita.trim();
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        return uscita;
    } // fine del metodo

    /**
     * Elimina la coda terminale della stringa, se esiste.
     * <p>
     * Esegue solo se la stringa è valida. <br>
     * Se manca la coda, restituisce la stringa. <br>
     * Elimina spazi vuoti iniziali e finali. <br>
     *
     * @param entrata stringa in ingresso
     * @param coda da eliminare
     * @return uscita stringa convertita
     */
    public static String levaCoda(String entrata, String coda) {
        String uscita = entrata;

        if (entrata != null) {
            uscita = entrata.trim();
            if (coda != null) {
                coda = coda.trim();
                if (uscita.endsWith(coda)) {
                    uscita = uscita.substring(0, uscita.length() - coda.length());
                    uscita = uscita.trim();
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        return uscita;
    } // fine del metodo

    /**
     * Sostituisce tutte le occorrenze.
     * Esegue solo se il testo è valido
     * Se arriva un oggetto non stringa, restituisce l'oggetto
     *
     * @param testoIn in ingresso
     * @param oldStringa da eliminare
     * @param newStringa da sostituire
     * @return testoOut convertito
     */
    public static String sostituisce(String testoIn, String oldStringa, String newStringa) {
        def testoOut = testoIn
        int pos = 0
        String prima = VUOTA

        if (testoIn && oldStringa && newStringa) {
            testoOut = testoIn.trim()
            if (testoIn.contains(oldStringa)) {

                while (pos != INT_NULLO) {
                    pos = testoIn.indexOf(oldStringa)
                    if (pos != INT_NULLO) {
                        prima += testoIn.substring(0, pos)
                        prima += newStringa
                        pos += oldStringa.length()
                        testoIn = testoIn.substring(pos)
                    }// fine del blocco if
                }// fine di while

                testoOut = prima + testoIn
            }// fine del blocco if
        }// fine del blocco if

        return testoOut
    } // fine del metodo

    /**
     * Elimina (eventuali) doppie graffe in testa e coda della stringa.
     * Funziona solo se le graffe sono esattamente in TESTA ed in CODA alla stringa
     * Se arriva una stringa vuota, restituisce una stringa vuota
     * Elimina spazi vuoti iniziali e finali
     *
     * @param stringa in ingresso
     * @return stringa con doppie graffe eliminate
     */
    public static String setNoGraffe(String stringaIn) {
        String stringaOut = stringaIn

        if (stringaIn.length() > 0) {
            stringaOut = stringaIn.trim()
            stringaOut = levaTesta(stringaOut, GRAFFA_INI)
            stringaOut = levaTesta(stringaOut, GRAFFA_INI)
            stringaOut = levaCoda(stringaOut, GRAFFA_END)
            stringaOut = levaCoda(stringaOut, GRAFFA_END)
        }// fine del blocco if

        return stringaOut.trim()
    } // fine del metodo

    /**
     * Chiude il template
     *
     * Il testo inizia col template, ma prosegue (forse) anche oltre
     * Cerco la prima doppia graffa che abbia all'interno lo stesso numero di aperture e chiusure
     * Spazzola il testo fino a pareggiare le graffe
     * Se non riesce a pareggiare le graffe, ritorna una stringa nulla
     *
     * @param testo /template da spazzolare
     * @return template
     */
    public static String chiudeTmpl(String templateIn) {
        String templateOut
        int posIni = 0
        int posEnd = 0
        boolean pari = false

        templateOut = templateIn.substring(posIni, posEnd + GRAFFE_END.length()).trim()

        while (!pari) {
            posEnd = templateIn.indexOf(GRAFFE_END, posEnd + GRAFFE_END.length())
            if (posEnd != -1) {
                templateOut = templateIn.substring(posIni, posEnd + GRAFFE_END.length()).trim()
                pari = isPariGraffe(templateOut)
            } else {
                break
            }// fine del blocco if-else
        } //fine del ciclo while

        if (!pari) {
            templateOut = ''
        }// fine del blocco if

        return templateOut
    } // fine del metodo

    /**
     * Estrae il testo di un template dal testo completo della voce
     * Esamina il PRIMO template che trova
     * Gli estremi sono COMPRESI
     *
     * Recupera il tag iniziale con o senza ''Template''
     * Recupera il tag finale di chiusura con o senza ritorno a capo precedente
     * Controlla che non esistano doppie graffe dispari all'interno del template
     */
    public static String estraeTmplCompresi(String testo, String tag) {
        String template = ''
        boolean continua = false
        def reg = null
        def matcher = null
        int posIni
        int posEnd
        String tagIniTemplate = ''

        // controllo di congruita
        if (testo && tag) {
            reg = /\{\{(Template:)?${tag}/
            continua = (reg)
        }// fine del blocco if

        if (continua) {
            matcher = (testo =~ reg)
            continua = matcher.find()
        }// fine del blocco if

        if (continua) {
            tagIniTemplate = matcher[0][0]
            if (tagIniTemplate) {
                continua = true
            } else {
                continua = false
            }// fine del blocco if-else
        }// fine del blocco if

        // controlla se esiste una doppia graffa di chiusura
        // non si sa mai
        if (continua) {
            posIni = testo.indexOf(tagIniTemplate)
            posEnd = testo.indexOf(GRAFFE_END, posIni)
            template = testo.substring(posIni)
            continua == (posEnd == -1)
        }// fine del blocco if

        // cerco la prima doppia graffa che abbia all'interno
        // lo stesso numero di aperture e chiusure
        // spazzola il testo fino a pareggiare le graffe
        if (continua) {
            template = chiudeTmpl(template)
        }// fine del blocco if

        return template
    }// fine del metodo

    /**
     * Estrae il testo di un template dal testo completo della voce
     * Esamina il PRIMO template che trova
     * Gli estremi sono ESCLUSI
     *
     * Recupera il tag iniziale con o senza ''Template''
     * Recupera il tag finale di chiusura con o senza ritorno a capo precedente
     * Controlla che non esistano doppie graffe dispari all'interno del template
     */
    public static String estraeTmplEsclusi(String testo, String tag) {
        String template = estraeTmplCompresi(testo, tag)
        template = setNoGraffe(template)
        return template.trim()
    }// fine del metodo

    /**
     * Estrae il testo di un template BIO dal testo completo della voce
     * Esamina il PRIMO template che trova (ce ne dovrebbe essere solo uno)
     * Gli estremi sono COMPRESI
     *
     * Recupera il tag iniziale con o senza ''Template''
     * Recupera il tag finale di chiusura con o senza ritorno a capo precedente
     * Controlla che non esistano doppie graffe dispari all'interno del template
     */
    public static String estraeTmplBioCompresi(String testo) {
        return estraeTmplCompresi(testo, BIO)
    }// fine del metodo

    /**
     * Estrae il testo di un template BIO dal testo completo della voce
     * Esamina il PRIMO template che trova
     * Gli estremi sono ESCLUSI
     *
     * Recupera il tag iniziale con o senza ''Template''
     * Recupera il tag finale di chiusura con o senza ritorno a capo precedente
     * Controlla che non esistano doppie graffe dispari all'interno del template
     */
    public static String estraeTmplBioEsclusi(String testo) {
        return estraeTmplEsclusi(testo, BIO)
    }// fine del metodo

    /**
     * Crea una mappa standard (valori String) dal testo JSON di una pagina
     *
     * @param text in ingresso
     * @return mappa standard (valori String)
     */
    public static LinkedHashMap creaMappa(String textJSON) {
        LinkedHashMap mappa = null
        LazyMap lazyMap
        def result
        JsonSlurper slurper = new JsonSlurper()
        result = slurper.parseText(textJSON)

        if (result."${QUERY}") {
            if (result."${QUERY}"."${PAGES}") {
                lazyMap = result."${QUERY}"."${PAGES}"
                mappa = fixMappa(lazyMap)
            }// fine del blocco if
        } else {
            mappa = fixMappaJson(result)
        }// fine del blocco if-else

        return mappa
    } // fine del metodo

    /**
     * Crea una mappa standard (valori String) da una mappa JSON di una pagina
     *
     * Prima i parametri delle info
     * Poi, se ci sono, i parametri della revisione
     *
     * @param mappa JSON in ingresso
     * @return mappa standard (valori String)
     */
    private static LinkedHashMap fixMappa(def mappaIn) {
        LinkedHashMap mappaOut = new LinkedHashMap()
        def mappaValoriInfo = mappaIn.values()
        ArrayList mappaValoriRev = null
        String key
        Object value

        //--valori dei parametri ricavati dalle info della pagina
        PagePar.getInf().each {
            key = it.toString()
            value = mappaValoriInfo["${key}"].get(0)
            mappaOut.put(key, value)
        } // fine del ciclo each

        //--controlla che esistano i valori della revisione
        if (mappaValoriInfo["${REVISIONS}"].get(0)) {
            mappaValoriRev = mappaValoriInfo["${REVISIONS}"].get(0)
        }// fine del blocco if

        //--valori dei parametri ricavati dall'ultima revisione della pagina
        if (mappaValoriRev) {
            PagePar.getRev().each {
                key = it.toString()
                value = mappaValoriRev["${key}"].get(0)
                //--patch per il nome 'atipico' del campo text
                if (it == PagePar.text && !value) {
                    value = mappaValoriRev["*"].get(0)
                }// fine del blocco if
                mappaOut.put(key, value)
            } // fine del ciclo each
        }// fine del blocco if

        return mappaOut
    } // fine del metodo

    /**
     * Crea una mappa standard (valori String) da una mappa JSON di una pagina
     *
     * Prima i parametri delle info
     * Poi, se ci sono, i parametri della revisione
     *
     * @param mappa JSON in ingresso
     * @return mappa standard (valori String)
     */
    private static LinkedHashMap fixMappaJson(def mappaIn) {
        LinkedHashMap mappaOut = new LinkedHashMap()
        String key
        Object value

        //--valori dei parametri ricavati dal testo json
        PagePar.getPerm2().each {
            key = it.toString()
            value = mappaIn["${key}"]
            mappaOut.put(key, value)
        } // fine del ciclo each

        return mappaOut
    } // fine del metodo

    /**
     * Converte i typi di una mappa secondo i parametri PagePar
     *
     * @param mappa standard (valori String) in ingresso
     * @return mappa typizzata secondo PagePar
     */
    public static LinkedHashMap converteMappa(LinkedHashMap mappaIn) {
        LinkedHashMap mappaOut = new LinkedHashMap()
        String key
        String valueTxt
        def valueObj = null

        mappaIn?.each {
            key = it.key
            valueTxt = (String) it.value
            valueObj = fixValueMap(key, valueTxt)
            mappaOut.put(key, valueObj)
        } // fine del ciclo each

        return mappaOut
    } // fine del metodo

    /**
     * Converte il valore stringa nel tipo previsto dal parametro PagePar
     *
     * @param mappa standard (valori String) in ingresso
     * @return mappa typizzata secondo PagePar
     */
    private static fixValueMap(String key, String valueTxt) {
        def valueObj = null
        PagePar.TypeField typo = PagePar.getParField(key)

        switch (typo) {
            case PagePar.TypeField.string:
                valueObj = valueTxt
                break;
            case PagePar.TypeField.integerzero:     //--conversione degli interi
            case PagePar.TypeField.integernotzero:  //--conversione degli interi
                try { // prova ad eseguire il codice
                    valueObj = Integer.decode(valueTxt)
                } catch (Exception unErrore) { // intercetta l'errore
                    unErrore = null
                    valueObj = 0
                }// fine del blocco try-catch
                break;
            case PagePar.TypeField.date:            //--conversione delle date
                try { // prova ad eseguire il codice
                    valueObj = convertTxtData(valueTxt)
                } catch (Exception unErrore) { // intercetta l'errore
                    unErrore = null
                    valueObj = DATA_NULLA
                }// fine del blocco try-catch
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return valueObj
    } // fine del metodo

    /**
     * Crea una lista di pagine (valori pageids) dal titolo di una categoria
     */
    public static ArrayList<Integer> creaListaCat(String title) {
        ArrayList<Integer> lista = null
        QueryCat query = new QueryCat(title)

        if (query) {
            lista = query.getListaPageids()
        }// fine del blocco if

        return lista
    }// end of method

    /**
     * Crea una lista di pagine (valori pageids) dal testo JSON di una pagina
     *
     * @param text in ingresso
     * @return lista pageid (valori Integer)
     */
    public static ArrayList<Integer> creaListaCatJson(String textJSON) {
        ArrayList<Integer> lista = null
        ArrayList listaTmp = null
        def result
        JsonSlurper slurper = new JsonSlurper()
        result = slurper.parseText(textJSON)

        if (result."${QUERY}") {
            if (result."${QUERY}"."${CATEGORY_MEMBERS}") {
                listaTmp = result."${QUERY}"."${CATEGORY_MEMBERS}"
                lista = converteListaCat(listaTmp)
            }// fine del blocco if
        }// fine del blocco if-else

        return lista
    } // fine del metodo

    /**
     * Estrae il valore del parametro continue dal testo JSON di una pagina
     *
     * @param text in ingresso
     * @return parametro continue
     */
    public static String creaCatContinue(String textJSON) {
        String textContinue = ''
        LazyMap lazyMap
        def result
        JsonSlurper slurper = new JsonSlurper()
        result = slurper.parseText(textJSON)

        if (result."${QUERY_CONTINUE}") {
            if (result."${QUERY_CONTINUE}"."${CATEGORY_MEMBERS}") {
                lazyMap = result."${QUERY_CONTINUE}"."${CATEGORY_MEMBERS}"
                textContinue = estraeContinue(lazyMap)
            }// fine del blocco if
        }// fine del blocco if-else

        return textContinue
    } // fine del metodo

    /**
     * Estrae il valore del parametro continue dalla mappa
     *
     * @param text in ingresso
     * @return parametro continue
     */
    private static String estraeContinue(LazyMap lazyMap) {
        String textContinue = ''
        def mappaValori
        String valore
        String sep = '\\|'
        def parti

        mappaValori = lazyMap.values()
        valore = mappaValori[0]
        parti = valore.split(sep)
        textContinue = parti[1]

        //@todo rimetto il valore intero (e non le parti) perché così adesso funziona
        return valore
    } // fine del metodo

    /**
     * Converte i typi di una mappa secondo i parametri PagePar
     *
     * La mappa in ingresso contiene ns, pageid e title
     * Utilizzo solo il pageid (Integer)
     *
     * @param mappa standard (valori String) in ingresso
     * @return mappa typizzata secondo PagePar
     */
    private static ArrayList<Integer> converteListaCat(ArrayList listaIn) {
        ArrayList<Integer> lista = new ArrayList<Integer>()
        int value

        listaIn?.each {
            value = (int) it[PAGEID]
            lista.add(value)
        } // fine del ciclo each

        return lista
    } // fine del metodo

    /**
     * Crea una stringa di testo, con tutti i valori della lista, separati dal pipe
     *
     * @param lista (valori Integer) in ingresso
     * @return stringa di valori
     */
    public static String creaListaPageids(ArrayList<Integer> lista) {
        String testo = ''
        String sep = '|'

        lista?.each {
            testo += it
            testo += sep
        } // fine del ciclo each
        testo = levaCoda(testo, sep)

        return testo
    } // fine del metodo

    /**
     * Converte il valore stringa della data in una data
     * Formato: 2015-06-30T10:18:05Z
     *
     * @param dataTxt in ingresso
     * @return data in uscita
     */
    public static Date convertTxtData(String dataTxt) {
        String annoStr
        String meseStr
        String giornoStr
        String oraStr
        String minutoStr
        String secondoStr
        int anno = 0
        int mese = 0
        int giorno = 0
        int ora = 0
        int minuto = 0
        int secondo = 0

        annoStr = dataTxt.substring(0, 4)
        meseStr = dataTxt.substring(5, 7)
        giornoStr = dataTxt.substring(8, 10)
        oraStr = dataTxt.substring(11, 13)
        minutoStr = dataTxt.substring(14, 16)
        secondoStr = dataTxt.substring(17, 19)

        anno = Integer.decode(annoStr)
        mese = Integer.decode(meseStr)
        giorno = Integer.decode(giornoStr)
        ora = Integer.decode(oraStr)
        minuto = Integer.decode(minutoStr)
        secondo = Integer.decode(secondoStr)

        //--patch
        anno = anno - 1900
        mese = mese - 1

        return new Date(anno, mese, giorno, ora, minuto, secondo)
    }// fine del metodo

    private static String apici(def entrata) {
        return APICI + entrata + APICI
    }// fine del metodo

    private static String graffe(def entrata) {
        return GRAFFA_INI + entrata + GRAFFA_END
    }// fine del metodo

} // fine della classe astratta
