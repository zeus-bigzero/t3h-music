package edu.t3h.music

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.t3h.music.adapter.MusicAdapter
import edu.t3h.music.adapter.OnItemClickListener
import edu.t3h.music.databinding.ActivityMainBinding
import edu.t3h.music.ext.convertTime
import edu.t3h.music.ext.covert
import edu.t3h.music.model.SSMusic

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() {
            return requireNotNull(_binding)
        }
    private lateinit var adapter: MusicAdapter
    private val musicPlayer: MusicPlayer by lazy {
        MusicPlayer.getInstance().apply {
            iCompleteListener = object : ICompleteListener {
                override fun getCurrentPosition(position: Int) {
                    binding.timeBar.progress = position

                    runOnUiThread {
                        binding.tvCurrent.text = position.convertTime()
                    }
                }

                override fun getEndDuration(duration: Int) {
                    binding.timeBar.max = duration

                    binding.tvEnd.text = duration.convertTime()
                }
            }
        }
    }
    private var isPlaying: Boolean = false
    private val music = arrayListOf<SSMusic>()
    private var ssMusicIsPlaying: SSMusic? = null
    private val sharedPref: SharedPreferences by lazy {
        getSharedPreferences("t3h_music", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Mockup.getMusic().forEach {
            val ssMusic = it.covert().apply {
                isFavorite = sharedPref.getBoolean("$rawId", false)
            }
            music.add(ssMusic)
        }
        adapter = MusicAdapter(music, onClickItem = {
            isPlaying = true
            ssMusicIsPlaying = it
            musicPlayer.play(this, it.rawId)
            binding.ivPlayPause.setImageResource(R.drawable.baseline_pause_24)
        }, onItemClickListener = object : OnItemClickListener {
            override fun onFavorite(rawId: Int, isFavorite: Boolean) {
                if (isFavorite) {
                    sharedPref.edit().putBoolean("$rawId", false).apply()
                } else {
                    sharedPref.edit().putBoolean("$rawId", true).apply()
                }
            }

        })

        binding.ivPlayPause.setOnClickListener {
            if (isPlaying) {
                isPlaying = false
                musicPlayer.pause()
                binding.ivPlayPause.setImageResource(R.drawable.icon_play)
            } else {
                isPlaying = true
                musicPlayer.resume()
                binding.ivPlayPause.setImageResource(R.drawable.baseline_pause_24)
            }
        }

        binding.ivNext.setOnClickListener {
            ssMusicIsPlaying?.let { item ->
                val index = music.indexOf(item)
                if (index < 0) {
                    return@let
                }
                if (index == music.size - 1) {
                    return@let
                }

                ssMusicIsPlaying = music[index + 1]
                ssMusicIsPlaying?.let {
                    musicPlayer.stop()
                    musicPlayer.play(this, it.rawId)
                }
            }
        }

        binding.ivPrev.setOnClickListener {
            ssMusicIsPlaying?.let { item ->
                val index = music.indexOf(item)
                if (index < 0) {
                    return@let
                }
                if (index == 0) {
                    return@let
                }

                ssMusicIsPlaying = music[index - 1]
                ssMusicIsPlaying?.let {
                    musicPlayer.stop()
                    musicPlayer.play(this, it.rawId)
                }
            }
        }

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
                clipToPadding = false
                setPadding(0, 0, 0, 300)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        musicPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}