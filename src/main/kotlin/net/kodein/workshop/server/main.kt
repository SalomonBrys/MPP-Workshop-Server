package net.kodein.workshop.server

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import net.kodein.workshop.server.data.Contact
import org.dizitart.kno2.getRepository
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.kodein.di.ktor.controller.controller
import org.kodein.di.ktor.kodein

fun Application.module() {
    install(DefaultHeaders)
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost()
    }
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    kodein {
        bind() from singleton {
            nitrite {
                path = "contacts.db"
            }.apply(Nitrite::init)
        }

        bind() from provider { instance<Nitrite>().getRepository<Contact>() }
    }

    install(Routing) {
        controller("/api/contacts") { APIController(instance()) }
    }
}

fun main() {
    embeddedServer(Netty, 8042, watchPaths = listOf("MPP-Workshop-Server"), module = Application::module).start()
}
