package edu.t3h.music

import edu.t3h.lib_assets_music.R

object Mockup {

    fun getMusic(): List<MusicT3H> {
        return listOf(
            MusicT3H(name = "Anh thương em rồi", rawId = R.raw.anh_thuong_em_roi),
            MusicT3H(name = "Có sao cũng đành", rawId = R.raw.co_sao_cung_danh),
            MusicT3H(name = "Deja Vu", rawId = R.raw.deja_vu),
            MusicT3H(name = "Hãy trao cho anh", rawId = R.raw.hay_trao_cho_anh),
            MusicT3H(name = "Magnetic", rawId = R.raw.magnetic),
            MusicT3H(name = "Người như anh", rawId = R.raw.nguoi_nhu_anh),
            MusicT3H(name = "Sheesh", rawId = R.raw.sheesh),
            MusicT3H(name = "Thuỷ triều", rawId = R.raw.thuy_trieu),
            MusicT3H(name = "Tiếng nói miền tây", rawId = R.raw.tieng_noi_mien_tay),
            MusicT3H(name = "021-Binz", rawId = R.raw.binz),
            MusicT3H(name = "This Way", rawId = R.raw.this_way),
            MusicT3H(name = "Thiên đàng", rawId = R.raw.thien_dang),
            MusicT3H(name = "Sơn tinh thuỷ tin Rap Việt", rawId = R.raw.son_tinh_thuy_tinh_rap_viet),
            MusicT3H(name = "Ông bà già tao lo hết", rawId = R.raw.ong_ba_gia_tao_lo_het),
            MusicT3H(name = "Hạt cát bụi vàng", rawId = R.raw.hat_cat_bui_vang),
            MusicT3H(name = "Cô gái vàng", rawId = R.raw.co_gai_vang)
        )
    }
}