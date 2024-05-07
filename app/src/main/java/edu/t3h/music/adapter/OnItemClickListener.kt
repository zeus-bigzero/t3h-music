package edu.t3h.music.adapter

interface OnItemClickListener {
    /**
     * isFavorite: true -> ng dùng xoá bài hát khỏi danh sách yêu thích
     * false -> ngược lại
     */
    fun onFavorite(id: Int, isFavorite: Boolean)
}