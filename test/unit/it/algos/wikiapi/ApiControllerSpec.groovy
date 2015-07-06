package it.algos.wikiapi

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(ApiController)
class ApiControllerSpec extends Specification {

    private static String TITOLO = 'Nicola Conte (ufficiale)'
    private static String PAGE_ID = 698528
    private static String TITOLO_CAT_BREVE = 'Eventi del 1902'
    private static String TITOLO_CAT_MEDIA = 'Nati nel 1420'

    def setup() {
        controller.apiService = new ApiService()
    }

    def cleanup() {
    }

    /**
     * Senza parametri
     */
    void 'test query'() {
        when:
        controller.query()

        then:
        assert response.text == ApiController.MANCA
    }// end of single test

    /**
     * Solo i 3 parametri base che arrivano sempre (in runtime)
     */
    void 'test query2'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        controller.query()

        then:
        assert response.text == ApiController.MANCA
    }// end of single test

    /**
     * Un solo parametro significativo (oltre ai 3 fissi)
     * Parametro title
     * Return la pagina
     */
    void 'test query3'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        controller.query()

        then:
        assert response.text.startsWith('{"pageid":"698528","')
        assert response.text.endsWith('[[Categoria:Italo-libici|Conte, Nicola]]"}')
    }// end of single test

    /**
     * Parametro type == voce
     * Return la voce
     */
    void 'test query4'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        params.type = 'voce'
        controller.query()

        then:
        assert response.text.startsWith('{{F|militari')
        assert response.text.endsWith('[[Categoria:Italo-libici|Conte, Nicola]]')
    }// end of single test

    /**
     * Parametro type == template
     * Non sufficente, perché manca il tag del template
     * Return vuoto
     */
    void 'test query5'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        params.type = 'template'
        controller.query()

        then:
        assert response.text == ''
    }// end of single test

    /**
     * Parametro type == template
     * Parametro nome == Bio
     * Return il template
     */
    void 'test query6'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        params.type = 'template'
        params.nome = 'Bio'
        controller.query()

        then:
        assert response.text.startsWith('{{Bio')
        assert response.text.endsWith('italiano\n}}')
    }// end of single test

    /**
     * Parametro type == template
     * Parametro nomeTmp == Bio
     * Return il template
     */
    void 'test query7'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        params.type = 'template'
        params.nomeTmp = 'Bio'
        controller.query()

        then:
        assert response.text.startsWith('{{Bio')
        assert response.text.endsWith('italiano\n}}')
    }// end of single test

    /**
     * Parametro type == template
     * Parametro template == Bio
     * Return il template
     */
    void 'test query8'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO
        params.type = 'template'
        params.template = 'Bio'
        controller.query()

        then:
        assert response.text.startsWith('{{Bio')
        assert response.text.endsWith('italiano\n}}')
    }// end of single test

    /**
     * Parametro id
     * Return la pagina
     */
    void 'test query9'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.id = PAGE_ID
        controller.query()

        then:
        assert response.text.startsWith('{"pageid":"698528","')
        assert response.text.endsWith('[[Categoria:Italo-libici|Conte, Nicola]]"}')
    }// end of single test

    /**
     * Parametro id
     * Parametro type == template
     * Parametro template == Bio
     * Return il template
     */
    void 'test query10'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.id = PAGE_ID
        params.type = 'template'
        params.nomeTmp = 'Bio'
        controller.query()

        then:
        assert response.text.startsWith('{{Bio')
        assert response.text.endsWith('italiano\n}}')
    }// end of single test

    /**
     * Parametro id
     * Parametro type == categoria
     * Return la lista di pageids delle pagine della categoria
     */
    void 'test query11'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO_CAT_BREVE
        params.type = 'categoria'
        controller.query()

        then:
        assert response.text.equals('4784|2066327')
        def stop
    }// end of single test

    /**
     * Parametro id
     * Parametro type == categoria
     * Return la lista di pageids delle pagine della categoria
     */
    void 'test query12'() {
        when:
        params.controller = 'api'
        params.format = 'null'
        params.action = 'query'
        params.title = TITOLO_CAT_MEDIA
        params.type = 'categoria'
        controller.query()

        then:
        assert response.text.equals('1302369|5228547|4546347|1984650|2769897|2919069|3920247|335164|892236|4107898|4603132|2619620|4171223|4616392|2349448|277859|2581985|3824059|4509757|68319|4559856|1681148|465540|2949655|2852029|1609386|1111411|2926313|5420101|3157646|2129891|4524750|308941')
        def stop
    }// end of single test

//    /**
//     * Parametri possibili (oltre ai 3 fissi di sistema):
//     * title
//     */
//    void 'test download'() {
//        when:
//        params.controller = 'api'
//        params.format = 'null'
//        params.action = 'download'
//        params.title = TITOLO
//        controller.download()
//
//        then:
//        assert response.text.equals('Registrata la pagina: ' + TITOLO)
//    }// end of single test
//
//    /**
//     * Parametri possibili (oltre ai 3 fissi di sistema):
//     * title
//     */
//    void 'test downloadBio'() {
//        when:
//        params.controller = 'api'
//        params.format = 'null'
//        params.action = 'downloadBio'
//        params.title = TITOLO
//        controller.download()
//
//        then:
//        assert response.text.startsWith('{"pageid":"698528","')
//        assert response.text.equals('Registrata la pagina: ' + TITOLO)
//    }// end of single test

}// end of testing class



