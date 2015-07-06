package it.algos.wikiapi

/**
 * Query standard per leggere/scrivere il risultato di una pagina
 * NON legge le categorie
 * Usa il titolo della pagina o il pageid (a seconda della sottoclasse concreta utilizzata)
 * Legge o scrive (a seconda della sottoclasse concreta utilizzata)
 * Legge le informazioni base della pagina (oltre al risultato)
 * Legge una sola Pagina con le informazioni base
 * Necessita di Login per scrivere, non per leggere solamente
 */
public abstract class QueryPage extends Query {

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse
     */
    public QueryPage(String titlepageid, TipoRicerca tipoRicerca, TypoRequest tipoRequest) {
        super(titlepageid, tipoRicerca, tipoRequest)
    }// fine del metodo costruttore

    /**
     * Costruttore completo
     * Rinvia al costruttore della superclasse
     */
    public QueryPage(int pageid, TipoRicerca tipoRicerca, TypoRequest tipoRequest) {
        super(pageid, tipoRicerca, tipoRequest)
    }// fine del metodo costruttore

}// end of class
