package dev.j30ng.drol.receiver.struct.req

import dev.j30ng.drol.pusher.EmailPusher
import dev.j30ng.drol.pusher.PushMethod
import dev.j30ng.drol.pusher.SlackPusher
import dev.j30ng.drol.pusher.struct.PushData
import dev.j30ng.drol.receiver.struct.Validatable


enum class PushType(val pusher: PushMethod) {
    Email(EmailPusher()),
    Slack(SlackPusher())
}

data class PushRequest(
        val user: String,
        val pushType: PushType,
        val data: PushData
): Validatable {
    override fun validate() {
        println("validating user=$user, channel=$pushType, data=$data")
        if (user == null) throw NullPointerException("user is null")
        if (pushType == null) throw NullPointerException("pushType is null")
        if (data == null) throw NullPointerException("data is null")
        return
    }
}

