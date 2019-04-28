package dev.j30ng.drol.pusher

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.append
import io.ktor.http.withCharset
import dev.j30ng.drol.pusher.struct.PushData
import dev.j30ng.drol.pusher.struct.slack.SlackMessage
import java.util.*

class SlackPusher: PushMethod {
    override suspend fun push(user: String, data: PushData) {
        postMessage(
            SlackMessage(
                channel = getChannelId(user, data),
                text = formatText(data)
            )
        )
    }
}

private val apacheHttpClient = HttpClient(Apache) {
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
}

private object SlackPusherConfig {
    init {
        ClassLoader.getSystemResourceAsStream("slack-pusher.properties")!!.use {
            this@SlackPusherConfig.properties.load(it)
        }
    }

    private val properties: Properties = Properties()
    val token: String by lazy { properties["token"] as String }
}

private suspend fun postMessage(message: SlackMessage) {
    val url = "https://slack.com/api/chat.postMessage"
    apacheHttpClient.run {
        post<String>(url) {
            body = message
            headers.apply {
                append("Authorization", "Bearer ${SlackPusherConfig.token}")
                append("Content-type", ContentType.Application.Json.withCharset(Charsets.UTF_8))
            }
        }
    }
}

private fun getChannelId(user: String, data: PushData): String {
    return "CJ8J64VL5"
}

private const val horizontalLine: String = "-----------------------"

private fun formatText(data: PushData): String =
    listOf(
        listOf("[${data.title}]", data.content).joinToString("\n"),
        listOf(data.tags.joinToString(" ") { "#$it" }, horizontalLine).joinToString("\n")
    ).joinToString("\n\n")

