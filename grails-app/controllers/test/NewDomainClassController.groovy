package test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NewDomainClassController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NewDomainClass.list(params), model:[newDomainClassInstanceCount: NewDomainClass.count()]
    }

    def show(NewDomainClass newDomainClassInstance) {
        respond newDomainClassInstance
    }

    def create() {
        respond new NewDomainClass(params)
    }

    @Transactional
    def save(NewDomainClass newDomainClassInstance) {
        if (newDomainClassInstance == null) {
            notFound()
            return
        }

        if (newDomainClassInstance.hasErrors()) {
            respond newDomainClassInstance.errors, view:'create'
            return
        }

        newDomainClassInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'newDomainClass.label', default: 'NewDomainClass'), newDomainClassInstance.id])
                redirect newDomainClassInstance
            }
            '*' { respond newDomainClassInstance, [status: CREATED] }
        }
    }

    def edit(NewDomainClass newDomainClassInstance) {
        respond newDomainClassInstance
    }

    @Transactional
    def update(NewDomainClass newDomainClassInstance) {
        if (newDomainClassInstance == null) {
            notFound()
            return
        }

        if (newDomainClassInstance.hasErrors()) {
            respond newDomainClassInstance.errors, view:'edit'
            return
        }

        newDomainClassInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NewDomainClass.label', default: 'NewDomainClass'), newDomainClassInstance.id])
                redirect newDomainClassInstance
            }
            '*'{ respond newDomainClassInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(NewDomainClass newDomainClassInstance) {

        if (newDomainClassInstance == null) {
            notFound()
            return
        }

        newDomainClassInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NewDomainClass.label', default: 'NewDomainClass'), newDomainClassInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'newDomainClass.label', default: 'NewDomainClass'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
