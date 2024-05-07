package edu.t3h.music.model

import androidx.annotation.RawRes

data class SSMusic(
    val id: Long,
    @RawRes val rawId: Int,
    val name: String,
    var isFavorite: Boolean = false
)