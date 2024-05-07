package edu.t3h.music

interface ICompleteListener {

    /**
     * Trả về cái position hiện tại player đang chơi
     */
    fun getCurrentPosition(position: Int)

    /**
     * Trả về tổng thời gian của bài hát
     */
    fun getEndDuration(duration: Int)
}