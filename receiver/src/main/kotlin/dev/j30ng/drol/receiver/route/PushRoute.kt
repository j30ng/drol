package dev.j30ng.drol.receiver.route

import dev.j30ng.drol.receiver.struct.Validatable
import dev.j30ng.drol.receiver.struct.req.PushRequest

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.push() {
    install(ContentNegotiation) {
        gson { }
    }
    post("/") {
        call.errorSafeReceive<PushRequest>()?.apply {
            pushType.pusher.push(user, data)
            call.respond(HttpStatusCode.OK)
        } ?: run {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

suspend inline fun <reified T: Validatable> ApplicationCall.errorSafeReceive(): T? {
    return try { receive<T>().also { it.validate() } }
    catch (e: Exception) { null }
}
