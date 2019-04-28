package dev.j30ng.drol.pusher

import dev.j30ng.drol.pusher.struct.PushData

interface PushMethod {
    suspend fun push(user: String, data: PushData)
}