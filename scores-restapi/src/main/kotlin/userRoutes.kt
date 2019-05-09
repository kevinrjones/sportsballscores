package com.knowledgespike.scores.rest

import com.knowledgespike.scores.service.IUsersService
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.userRoutes(scoresService: IUsersService) {

    route("/api") {
        get("/users") {
            scoresService.test()
        }

        get("/users/{userId}") {

        }

        post("/users") {

        }
    }

}