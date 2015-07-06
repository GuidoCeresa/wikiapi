package it.algos.wikiapi

/**
 * Query standard per scrivere il risultato di una pagina
 * NON legge le categorie
 * Usa il pageid della pagina
 * Necessita di Login
 */
public class QueryWritePageid extends QueryPage {

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse, specificando i flag
     */
    public QueryWritePageid(String title) {
        super(title, TipoRicerca.pageid, TipoRequest.write)
    }// fine del metodo costruttore

}// end of class
