package dev.j30ng.drol.receiver

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

const val port = 8080

fun main() {
    embeddedServer(Netty, port, module = Application::mainModule).start(wait = true)
}
