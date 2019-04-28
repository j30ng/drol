package dev.j30ng.drol.receiver.route

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.root() {
    get("/") {
        call.respond("Ahoy there")
    }
}