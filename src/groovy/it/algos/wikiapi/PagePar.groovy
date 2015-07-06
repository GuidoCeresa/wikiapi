package it.algos.wikiapi

public enum PagePar {

    //--parametri wiki base
    pageid(true,true, TypePar.letturascrittura, TypeField.integernotzero),
    ns(true,true, TypePar.letturascrittura, TypeField.integerzero),
    title(true,true, TypePar.letturascrittura, TypeField.string),

    //--parametri wiki info
            contentmodel(true,true, TypePar.letturascrittura, TypeField.string),
    pagelanguage(true,true, TypePar.letturascrittura, TypeField.string),
    touched(true,true, TypePar.letturascrittura, TypeField.date),
    lastrevid(true,true, TypePar.letturascrittura, TypeField.integernotzero),
    length(true,true, TypePar.letturascrittura, TypeField.integernotzero),
    csrftoken(false,true, TypePar.soloscrittura, TypeField.string),
    starttimestamp(false,true, TypePar.soloscrittura, TypeField.date),

    //--parametri wiki revisions
            revid(true,false, TypePar.letturascrittura, TypeField.integernotzero),
    parentid(true,false, TypePar.letturascrittura, TypeField.integerzero),
    user(true,false, TypePar.letturascrittura, TypeField.string),
    userid(true,false, TypePar.letturascrittura, TypeField.integerzero),
    timestamp(true,false, TypePar.letturascrittura, TypeField.date),
    size(true,false, TypePar.letturascrittura, TypeField.integernotzero),
    comment(true,false, TypePar.letturascrittura, TypeField.string),
    contentformat(true,false, TypePar.letturascrittura, TypeField.string),
    text(false,false, TypePar.letturascrittura, TypeField.string),

    missing(false,false, TypePar.provvisorio, TypeField.string),
    revisions(false,false, TypePar.provvisorio, TypeField.string)

    private boolean permanente
    private boolean info
    private TypePar typePar
    private TypeField typeField

    PagePar(boolean permanente, boolean info, TypePar typePar, TypeField typeField) {
        this.permanente = permanente
        this.info = info
        this.typePar = typePar
        this.typeField = typeField
    }// fine del metodo costruttore

    /**
     * Restituisce una collezione di tutti gli elementi
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getAll() {
        return values().toList()
    }// end of method

    /**
     * Restituisce il parametro, individuato dal nome
     *
     * @param nome base del parametro
     * @return parametro
     */
    public static PagePar getPar(String key) {
        PagePar par = null

        values().each {
            if (key.endsWith(it.toString())) {
                par = it
            }// fine del blocco if
        } // fine del ciclo each

        return par
    }// end of method

    /**
     * Restituisce il tipo di campo di un parametro, individuato dal nome
     *
     * @param nome base del parametro
     * @return tipo di campo
     */
    public static TypeField getParField(String key) {
        return getPar(key).getType()
    }// end of method

    /**
     * Restituisce una collezione limitata agli elementi permanenti col flag info valido
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getInf() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()
        TypePar typeParLoc

        values().each {
            typeParLoc = it.typePar
            if (typeParLoc != TypePar.provvisorio) {
                if (it.info) {
                    lista.add(it)
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method

    /**
     * Restituisce una collezione limitata agli elementi permanenti  col flag info NON valido
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getRev() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()
        TypePar typeParLoc

        values().each {
            typeParLoc = it.typePar
            if (typeParLoc != TypePar.provvisorio) {
                if (!it.info) {
                    lista.add(it)
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method

    /**
     * Restituisce una collezione degli elementi permanenti (per il database)
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getPerm2() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()

        values().each {
            if (it.typePar != TypePar.provvisorio) {
                lista.add(it)
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method

    /**
     * Restituisce una collezione degli elementi da restituire in lettura
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getRead() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()

        values().each {
            if (it.typePar == TypePar.letturascrittura) {
                lista.add(it)
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method

    /**
     * Restituisce una collezione degli elementi da restituire in lettura e scrittura
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getWrite() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()

        values().each {
            if (it.typePar == TypePar.letturascrittura || it.typePar == TypePar.soloscrittura) {
                lista.add(it)
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method

    /**
     * Restituisce una collezione degli elementi obbligatori
     *
     * @return collezione
     */
    public static ArrayList<PagePar> getPerm() {
        ArrayList<PagePar> lista = new ArrayList<PagePar>()

        values().each {
            if (it.permanente) {
                lista.add(it)
            }// fine del blocco if
        } // fine del ciclo each

        return lista
    }// end of method


    public TypeField getType() {
        return typeField
    }// end of method

    /**
     * Controlla che tutti i parametri abbiano un valore valido
     *
     * @param mappa dei valori
     * @return true se tutti sono validi
     */
    public static boolean isParValidi(LinkedHashMap mappa) {
        boolean status = true
        String key
        Object value

        getPerm().each {
            key = it.toString()
            value = mappa."${key}"
            if (!isParValido(it, value)) {
                status = false
            }// fine del blocco if
        } // fine del ciclo each

        return status
    }// end of method

    /**
     * Controlla che il parametro abbia un valore valido
     *
     * @param parametro
     * @param valore del parametro
     * @return true se il valore è valido
     */
    private static boolean isParValido(PagePar par, Object value) {
        boolean status = false
        TypeField type = par.getType()

        switch (type) {
            case TypeField.string:
                if (value && value in String) {
                    status = true
                }// fine del blocco if
                break;
            case TypeField.integerzero:
                if (value in Integer) {
                    status = true
                }// fine del blocco if
                break;
            case TypeField.integernotzero:
                if (value in Integer && value > 0) {
                    status = true
                }// fine del blocco if
                break;
            case TypeField.date:
                if (value && value in Date) {
                    status = true
                }// fine del blocco if
                break;
            default: // caso non definito
                break;
        } // fine del blocco switch
        return status
    }// end of method

    /**
     * Enumeration di tipologie dei campi
     */
    public static enum TypePar {
        sololettura, letturascrittura, soloscrittura, provvisorio
    }// fine della Enumeration interna

    /**
     * Enumeration di tipologie dei campi
     */
    public static enum TypeField {
        string, integerzero, integernotzero, date
    }// fine della Enumeration interna

} // fine della Enumeration
