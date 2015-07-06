package it.algos.wikiapi

/**
 * Query standard per leggere il risultato di una pagina
 * NON legge le categorie
 * Usa il titolo della pagina
 * Non necessita di Login
 */
public class QueryReadTitle extends QueryPage {

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse, specificando i flag
     */
    public QueryReadTitle(String title) {
        super(title, TipoRicerca.title, TypoRequest.read)
    }// fine del metodo costruttore

}// end of class
