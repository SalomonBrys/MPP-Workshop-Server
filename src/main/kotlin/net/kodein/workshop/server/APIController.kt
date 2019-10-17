package net.kodein.workshop.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import net.kodein.workshop.server.data.Contact
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.FindOptions
import org.dizitart.no2.SortOrder
import org.dizitart.no2.objects.ObjectRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.ktor.controller.KodeinController
import org.kodein.di.ktor.kodein
import java.util.*

class APIController(application: Application) : KodeinController {

    override val kodein: Kodein by kodein { application }

    val contacts: ObjectRepository<Contact> by instance()

    override fun Route.getRoutes() {

        route("/") {
            get {
                call.respond(contacts.find(FindOptions.sort("fullName", SortOrder.Ascending)).map { mapOf("id" to it.id, "name" to it.name) }.toList())
            }

            put {
                val contact = call.receive<Contact>().copy(id = UUID.randomUUID().toString())
                val result = contacts.insert(contact)
                call.respond(mapOf("id" to contact.id))
            }
        }

        route("/{id}") {
            get {
                val contact = contacts.find(Contact::id eq call.parameters["id"]).firstOrNull()
                if (contact != null) call.respond(contact)
                else call.respond(HttpStatusCode.NotFound)
            }

            post {
                val contact = call.receive<Contact>()
                val result = contacts.update(contact, false)
                if (result.affectedCount == 0) call.respond(HttpStatusCode.NotFound)
                else call.respond(HttpStatusCode.OK)
            }
        }


    }

}
