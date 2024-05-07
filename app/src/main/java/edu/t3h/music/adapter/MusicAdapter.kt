package edu.t3h.music.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.t3h.music.R
import edu.t3h.music.databinding.ItemMusicBinding
import edu.t3h.music.model.SSMusic

class MusicAdapter(
    private val music: List<SSMusic>,
    private val onClickItem: ((SSMusic) -> Unit)? = null,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun getItemCount(): Int = music.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(music[position], position)
    }

    inner class MusicViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SSMusic, position: Int) {
            when (position % 5) {
                0 -> binding.ivThumbnail.setImageResource(R.drawable.icon_music_0)
                1 -> binding.ivThumbnail.setImageResource(R.drawable.icon_music_1)
                2 -> binding.ivThumbnail.setImageResource(R.drawable.icon_music_2)
                3 -> binding.ivThumbnail.setImageResource(R.drawable.icon_music_3)
                4 -> binding.ivThumbnail.setImageResource(R.drawable.icon_music_4)
            }
            binding.tvName.text = item.name
            if (item.isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
            binding.ivFavorite.setOnClickListener {
                onItemClickListener.onFavorite(item.rawId, item.isFavorite)

                item.isFavorite = !item.isFavorite
                if (item.isFavorite) {
                    binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }
        }
    }

}