package edu.t3h.music.ext

fun Int?.convertTime(): String {
    // 130s -> 2p10s

    if (this == null) return "--:--"
    val minute: Int = this / 60
    var sMinue = ""
    if (minute < 10) {
        sMinue = "0$minute"
    } else {
        sMinue = "$minute"
    }

    val seconds = this - minute * 60
    var sSeconds = ""

    if (seconds < 10) {
        sSeconds = "0$seconds"
    } else {
        sSeconds = "$seconds"
    }
    return "$sMinue:$sSeconds"

    /// I/O Stream
    // URI, Url
    // Xml, Json
}