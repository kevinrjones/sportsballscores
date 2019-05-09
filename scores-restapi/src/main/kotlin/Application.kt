package com.knowledgespike.scores.rest

import com.fasterxml.jackson.databind.SerializationFeature
import com.knowledgespike.scores.repository.UsersRepository
import com.knowledgespike.scores.service.IUsersService
import com.knowledgespike.scores.service.UsersService
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.auth.Authentication
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.routing.routing
import io.ktor.server.cio.CIO
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import org.koin.Logger.slf4jLogger
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

val restAppModule = module {
    single<IUsersService> { UsersService(get()) } // get() Will resolve HelloRepository
    single { UsersRepository() }
}


fun main(args: Array<String>) {
    embeddedServer(CIO, commandLineEnvironment(args)).start(true)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Koin) {
        slf4jLogger()
        modules(restAppModule)
    }

    val userService: IUsersService by inject()
    moduleWithDependencies(userService, testing)
}

fun Application.moduleWithDependencies(userService: IUsersService, testing: Boolean = false) {
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

    routing {
        if (isDev) trace {
            log.trace(it.buildText())
        }
        userRoutes(userService)
        scoresApiRoutes()
    }
}

data class MySession(val count: Int = 0)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

val Application.envKind get() = environment.config.property("ktor.environment").getString()
val Application.isDev get() = envKind == "dev"
val Application.isTest get() = envKind == "test"
val Application.isProd get() = envKind != "dev" && envKind != "test"
