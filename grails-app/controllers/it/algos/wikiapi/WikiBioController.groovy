package it.algos.wikiapi


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WikiBioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        respond WikiBio.list(params), model: [wikiBioInstanceCount: WikiBio.count()]
    }

    def show(WikiBio wikiBioInstance) {
        respond wikiBioInstance
    }

    def create() {
        respond new WikiBio(params)
    }

    @Transactional
    def save(WikiBio wikiBioInstance) {
        if (wikiBioInstance == null) {
            notFound()
            return
        }

        if (wikiBioInstance.hasErrors()) {
            respond wikiBioInstance.errors, view: 'create'
            return
        }

        wikiBioInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wikiBio.label', default: 'WikiBio'), wikiBioInstance.id])
                redirect wikiBioInstance
            }
            '*' { respond wikiBioInstance, [status: CREATED] }
        }
    }

    def edit(WikiBio wikiBioInstance) {
        respond wikiBioInstance
    }

    @Transactional
    def update(WikiBio wikiBioInstance) {
        if (wikiBioInstance == null) {
            notFound()
            return
        }

        if (wikiBioInstance.hasErrors()) {
            respond wikiBioInstance.errors, view: 'edit'
            return
        }

        wikiBioInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WikiBio.label', default: 'WikiBio'), wikiBioInstance.id])
                redirect wikiBioInstance
            }
            '*' { respond wikiBioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(WikiBio wikiBioInstance) {

        if (wikiBioInstance == null) {
            notFound()
            return
        }

        wikiBioInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WikiBio.label', default: 'WikiBio'), wikiBioInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wikiBio.label', default: 'WikiBio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
