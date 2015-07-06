package it.algos.wikiapi

import org.junit.Test

/**
 * Created by gac on 26 giu 2015.
 * Using specific Templates (Entity, Domain, Modulo) 
 */
public class QueryTest {

    private Query query
    private String contenuto

    @Test
    public void al() {

        query = new QueryReadTitle('Piozzano')
        contenuto = query.getRisultato()
        def stop
    }// end of single test

}// end of testing class
