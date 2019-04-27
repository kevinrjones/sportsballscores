package com.knowledgespike.scores.rest

import com.fasterxml.jackson.databind.SerializationFeature
import com.knowledgespike.scores.repository.ScoresRepository
import com.knowledgespike.scores.service.IScoresService
import com.knowledgespike.scores.service.ScoresService
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import org.koin.Logger.slf4jLogger
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.slf4j.event.Level
import kotlin.collections.set

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val helloAppModule = module {
    single<IScoresService> { ScoresService(get()) } // get() Will resolve HelloRepository
    single { ScoresRepository() }
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // val service: SomeService by inject()
    moduleWithDependencies(testing)
}

fun Application.moduleWithDependencies(testing: Boolean = false) {
    install(Sessions) {
        cookie<MySession>("SPORTSBALL_API") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Koin) {
        slf4jLogger()
        modules(helloAppModule)
    }

    routing {
        scoresApiRoutes()
    }
}

data class MySession(val count: Int = 0)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

