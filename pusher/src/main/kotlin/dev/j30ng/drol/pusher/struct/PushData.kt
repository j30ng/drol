package dev.j30ng.drol.pusher.struct

data class PushData(
    val title: String,
    val content: String,
    val tags: List<String>
)
