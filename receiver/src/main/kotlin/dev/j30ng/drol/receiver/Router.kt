package dev.j30ng.drol.receiver

import io.ktor.application.Application
import io.ktor.routing.route
import io.ktor.routing.routing
import dev.j30ng.drol.receiver.route.push
import dev.j30ng.drol.receiver.route.root

fun Application.mainModule() {
    routing {
        route("push") { push() }
        route("/") { root() }
    }
}
