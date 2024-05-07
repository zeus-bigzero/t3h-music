package edu.t3h.music

import androidx.annotation.RawRes

data class MusicT3H(
    val id: Long = System.currentTimeMillis() / 1000L,
    val name: String,
    @RawRes val rawId: Int,
    var isFavorite: Boolean = false
)