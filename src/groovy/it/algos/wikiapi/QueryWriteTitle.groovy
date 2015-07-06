package it.algos.wikiapi

/**
 * Query standard per scrivere il risultato di una pagina
 * NON legge le categorie
 * Usa il titolo della pagina
 * Necessita di Login
 */
public class QueryWriteTitle extends QueryPage{

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse, specificando i flag
     */
    public QueryWriteTitle(String title) {
        super(title, TipoRicerca.title, TipoRequest.write)
    }// fine del metodo costruttore

}// end of class
