package it.algos.wikiapi

/**
 * Query standard per leggere il risultato di una pagina
 * NON legge le categorie
 * Usa il pageid della pagina
 * Non necessita di Login
 */
public class QueryReadPageid extends QueryPage {

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse, specificando i flag
     */
    public QueryReadPageid(int pageid) {
        super(pageid, TipoRicerca.pageid, TypoRequest.read)
    }// fine del metodo costruttore

}// end of class
