package dev.j30ng.drol.pusher

import dev.j30ng.drol.pusher.struct.PushData

private data class Email(
    val title: String,
    val content: String
)

class EmailPusher: PushMethod {
    override suspend fun push(user: String, data: PushData) {
        println("Sending email to $user! (content: $data)")
        val recipientAddress = getEmailAddress(user)
        val email = format(data)
        send(recipientAddress, email)
    }
}

private fun format(data: PushData): Email =
    data.run {
        Email(title, content.let { content ->
            content + "\n\n" + tags.joinToString(", ") { tag -> "#$tag" }
        })
    }

private fun getEmailAddress(user: String): String {
    return "$user@unknown.com"
}

private fun send(recipientAddress: String, email: Email) {
    val senderAddress = "its.me@drol.gate"
    println("$senderAddress is sending email (title: $email.title, content: $email.content) to $recipientAddress")
}


