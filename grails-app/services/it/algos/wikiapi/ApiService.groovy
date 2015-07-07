package it.algos.wikiapi

import it.algos.algospref.Pref

class ApiService {

    static boolean transactional = false
    private final static String CAT_BIO = 'BioBot'
    private final static String CAT_DEBUG = 'Calciatori eritrei'

    /**
     * Legge dal server wiki
     * <p>
     * Rimanda al metodo completo
     * Di default suppone il title
     * Di default suppone la Pagina come ritorno
     *
     * @param title della pagina wiki
     * @return risultato della pagina (JSON)
     */
    public String legge(String title) {
        return legge(title, TipoRicerca.title)
    }// end of method

    /**
     * Legge dal server wiki
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return risultato della pagina (JSON) oppure della voce (text) oppure del template (text)
     */
    public String legge(def titlePageid, TipoRicerca tipoRicerca) {
        return legge(titlePageid, tipoRicerca, Type.pagina)
    }// end of method

    /**
     * Legge dal server wiki
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @param type di risposta
     * @return risultato della pagina (JSON) oppure della voce (text) oppure del template (text)
     */
    public String legge(def titlePageid, TipoRicerca tipoRicerca, Type type) {
        return legge(titlePageid, tipoRicerca, type, '')
    }// end of method

    /**
     * Legge dal server wiki
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @param type di risposta
     * @param tag del template
     * @return risultato della pagina (JSON) oppure della voce (text) oppure del template (text)
     */
    public String legge(def titlePageid, TipoRicerca tipoRicerca, Type type, String tag) {
        String testo = ''
        Page pagina

        switch (type) {
            case Type.pagina:
                pagina = leggePagina(titlePageid, tipoRicerca)
                testo = pagina.getJSON()
                break;
            case Type.voce:
                testo = leggeVoce(titlePageid, tipoRicerca)
                break;
            case Type.template:
                testo = leggeTmpl(titlePageid, tipoRicerca, tag)
                break;
            case Type.categoria:
                testo = leggeCat(titlePageid)
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return testo
    }// end of method

    /**
     * Legge dal server wiki
     * Registra la tavola base Wiki
     * <p>
     * @param title della pagina
     */
    public void download(String title) {
        Page pagina
        HashMap mappa
        Wiki wiki

        pagina = leggePagina(title)
        mappa = pagina.getMappa()
        wiki = new Wiki()
        wiki = fixMappa(wiki, mappa)
        wiki.save()
    }// end of method

    /**
     * Legge dal server wiki
     * Registra la tavola  WikiBio
     * <p>
     * @param title della pagina
     */
    public void downloadBio(String title) {
        downloadBio(leggePagina(title))
    }// end of method

    /**
     * Legge dal server wiki
     * Registra la tavola  WikiBio
     * <p>
     * @param title della pagina
     */
    public void downloadBio(int pageid) {
        downloadBio(leggePagina(pageid))
    }// end of method

    /**
     * Legge dal server wiki
     * Registra la tavola  WikiBio
     * <p>
     * @param title della pagina
     */
    public void downloadBio(Page pagina) {
        HashMap mappa
        WikiBio wiki
        String testoVoce
        String tmplBio

        testoVoce = pagina.getText()
        tmplBio = LibWiki.estraeTmplBioCompresi(testoVoce)

        if (tmplBio) {
            wiki = new WikiBio()
            mappa = pagina.getMappa()
            wiki = (WikiBio) fixMappa(wiki, mappa)
            wiki.tmplBio = tmplBio
            wiki.save(flush: true)
        }// fine del blocco if

    }// end of method

    /**
     * Esegue un ciclo di controllo e creazione di nuovi records
     * Controlla il flag USA_LIMITE_DOWNLOAD
     * Usa il numero massimo (MAX_DOWNLOAD) di voci da scaricare ad ogni ciclo (se USA_LIMITE_DOWNLOAD=true)
     * Legge la categoria BioBot
     * Legge le voci WikiBio esistenti
     * Trova la differenza
     * Scarica MAX_DOWNLOAD voci dal server e crea MAX_DOWNLOAD nuovi records di WikiBio
     */
    public void newBioCiclo() {
        ArrayList<Integer> listaTotaleCategoria
        ArrayList<Integer> listaEsistentiDataBase
        ArrayList<Integer> listaMancanti

        listaTotaleCategoria = LibWiki.creaListaCat(CAT_BIO)
        listaEsistentiDataBase = (ArrayList<Integer>) WikiBio.executeQuery("select pageid from WikiBio")
        listaMancanti = listaTotaleCategoria - listaEsistentiDataBase

        listaMancanti = listaParziale(listaMancanti)
        if (listaMancanti) {
            listaMancanti?.each {
                downloadBio(it)
            } // fine del ciclo each
        }// fine del blocco if

    }// end of method

    /**
     * Controlla il flag USA_LIMITE_DOWNLOAD
     * Usa il numero massimo (MAX_DOWNLOAD) di voci da scaricare ad ogni ciclo (se USA_LIMITE_DOWNLOAD=true)
     */
    private static ArrayList<Integer> listaParziale(ArrayList<Integer> listaIn) {
        ArrayList<Integer> listaOut = listaIn
        int limite = 0

        if (Pref.getBool(LibWiki.USA_LIMITE_DOWNLOAD)) {
            limite = Pref.getInt(LibWiki.MAX_DOWNLOAD)
            if (limite && limite < listaIn.size()) {
                listaOut = listaIn.subList(0, limite);
            }// fine del blocco if
        }// fine del blocco if

        return listaOut
    }// end of method

    /**
     * Regola i parametri della tavola in base alla mappa letta dal server
     * Aggiunge le date di riferimento lettura/scrittura
     */
    private static Wiki fixMappa(Wiki wiki, HashMap mappa) {
        ArrayList<PagePar> lista = PagePar.getPerm()
        String key
        def value

        lista?.each {
            key = it
            value = null
            if (mappa["${key}"]) {
                value = mappa["${key}"]
            }// fine del blocco if

            //--controllo degli interi che POSSONO esser anche zero
            if (it.type == PagePar.TypeField.integerzero) {
                if (!value) {
                    value = 0
                }// fine del blocco if
            }// fine del blocco if

            //--patch
            if (it == PagePar.comment) {
                if (value in String) {
                    if (value.startsWith('[[WP:OA|←]]')) {
                        value = 'Nuova pagina'
                    }// fine del blocco if
                }// fine del blocco if
            }// fine del blocco if

            try { // prova ad eseguire il codice
                wiki."${key}" = value
            } catch (Exception unErrore) { // intercetta l'errore
                log.error unErrore + ' : key= ' + key + ' value= ' + value
            }// fine del blocco try-catch
        } // fine del ciclo each

        return wiki
    }// end of method

    /**
     * Scrive dal server wiki
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param text testo completo della pagina
     * @param summary della modifica
     * @return flag di controllo
     */
    public String scrive(String title, String text, String summary) {
        return scrive(title, TipoRicerca.title, text, summary)
    }// end of method

    /**
     * Scrive dal server wiki
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @param text testo completo della pagina
     * @param summary della modifica
     * @return flag di controllo
     */
    public String scrive(def titlePageid, TipoRicerca tipoRicerca, String text, String summary) {
        return 'Ok, per ora'
    }// end of method

    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param title della pagina wiki
     * @return pagina grails costruita
     */
    public Page leggePagina(String title) {
        return leggePaginaTitle(title)
    }// end of method

    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public Page leggePagina(int pageid) {
        return leggePaginaPageid(pageid)
    }// end of method

    /**
     * Legge il risultato di una pagina
     *
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return pagina grails costruita
     */
    public Page leggePagina(def titlePageid, TipoRicerca tipoRicerca) {
        Page pagina = null

        switch (tipoRicerca) {
            case TipoRicerca.title:
                pagina = leggePaginaTitle(titlePageid)
                break;
            case TipoRicerca.pageid:
                if (titlePageid in Integer) {
                    pagina = leggePaginaPageid((int) titlePageid)
                } else {
                    try { // prova ad eseguire il codice
                        pagina = leggePaginaPageid(Integer.decode(titlePageid))
                    } catch (Exception unErrore) { // intercetta l'errore
                        log.error unErrore
                    }// fine del blocco try-catch
                }// fine del blocco if-else
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch

        return pagina
    }// end of method

    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param title della pagina wiki
     * @return pagina grails costruita
     */
    public Page leggePaginaTitle(String title) {
        Page pagina = null
        String contenuto
        Query read = new QueryReadTitle(title)

        if (read) {
            contenuto = read.getRisultato()
            pagina = new Page(contenuto)
        }// fine del blocco if

        return pagina
    }// end of method

    /**
     * Legge una pagina wiki e costruisce una pagina grails
     *
     * @param pageid della pagina wiki
     * @return pagina grails costruita
     */
    public Page leggePaginaPageid(int pageid) {
        Page pagina = null
        String contenuto
        Query read = new QueryReadPageid(pageid)

        if (read) {
            contenuto = read.getRisultato()
            pagina = new Page(contenuto)
        }// fine del blocco if

        return pagina
    }// end of method

    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param title della pagina wiki
     * @return risultato (solo testo) della voce
     */
    public String leggeVoce(String title) {
        String testo = ''
        Page pagina = leggePagina(title)

        if (pagina) {
            testo = pagina.getText()
        }// fine del blocco if

        return testo
    }// end of method

    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param pageid della pagina wiki
     * @return risultato (solo testo) della voce
     */
    public String leggeVoce(int pageid) {
        String testo = ''
        Page pagina = leggePagina(pageid)

        if (pagina) {
            testo = pagina.getText()
        }// fine del blocco if

        return testo
    }// end of method

    /**
     * Legge il risultato (testo) di una voce
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return risultato (solo testo) della voce
     */
    public String leggeVoce(def titlePageid, TipoRicerca tipoRicerca) {
        String testo = ''
        Page pagina = leggePagina(titlePageid, tipoRicerca)

        if (pagina) {
            testo = pagina.getText()
        }// fine del blocco if

        return testo
    }// end of method

    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param title della pagina wiki
     * @param tag del template
     * @return risultato del template
     */
    public String leggeTmpl(String title, String tag) {
        String tmpl = ''
        String testo = leggeVoce(title)

        if (testo && tag) {
            tmpl = LibWiki.estraeTmplCompresi(testo, tag)
        }// fine del blocco if

        return tmpl
    }// end of method

    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param pageid della pagina wiki
     * @param tag del template
     * @return risultato del template
     */
    public String leggeTmpl(int pageid, String tag) {
        String tmpl = ''
        String testo = leggeVoce(pageid)

        if (testo && tag) {
            tmpl = LibWiki.estraeTmplCompresi(testo, tag)
        }// fine del blocco if

        return tmpl
    }// end of method

    /**
     * Legge il template di una voce (il primo)
     * <p>
     * @param titlePageid (title oppure pageid)
     * @param tipoRicerca title o pageId
     * @return risultato del template
     */
    public String leggeTmpl(def titlePageid, TipoRicerca tipoRicerca, String tag) {
        String tmpl = ''
        String testo = leggeVoce(titlePageid, tipoRicerca)

        if (testo && tag) {
            tmpl = LibWiki.estraeTmplCompresi(testo, tag)
        }// fine del blocco if

        return tmpl
    }// end of method

    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param title della pagina wiki
     * @return risultato del template bio
     */
    public String leggeTmplBio(String title) {
        return leggeTmpl(title, Cost.TAG_BIO)
    }// end of method

    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param pageid della pagina wiki
     * @return risultato del template bio
     */
    public String leggeTmplBio(int pageid) {
        return leggeTmpl(pageid, Cost.TAG_BIO)
    }// end of method

    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param titlePageid (title oppure pageid)
     * @return risultato del template bio
     */
    public String leggeTmplBio(def titlePageid, TipoRicerca tipoQuery) {
        return leggeTmpl(titlePageid, tipoQuery, Cost.TAG_BIO)
    }// end of method

    /**
     * Legge il template bio di una voce (il primo)
     * <p>
     * @param titlePageid (title oppure pageid)
     * @return risultato del template bio
     */
    public String leggeTmplBio(String titlePageid, TipoRicerca tipoQuery) {
        String tmpl = ''
        String testo = leggeVoce(titlePageid, tipoQuery)

        if (testo) {
            tmpl = LibWiki.estraeTmplBioCompresi(testo)
        }// fine del blocco if

        return tmpl
    }// end of method

    /**
     * Legge i componenti di una categoria
     * <p>
     * @param title della categoria wiki
     * @return lista di pagine
     */
    public String leggeCat(String title) {
        String elenco = ''
        Query cat = new QueryCat(title)

        if (cat) {
            elenco = cat.getTxtPageids()
        }// fine del blocco if

        return elenco
    }// end of method


}// end of service class