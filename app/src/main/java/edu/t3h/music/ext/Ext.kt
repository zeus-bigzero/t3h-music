package edu.t3h.music.ext

import edu.t3h.music.MusicT3H
import edu.t3h.music.model.SSMusic

fun MusicT3H.covert(): SSMusic {
    return SSMusic(this.id, this.rawId, this.name, isFavorite = false)
}