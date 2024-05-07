package edu.t3h.music

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.annotation.IntegerRes
import androidx.annotation.RawRes
import java.lang.Exception
import java.util.Timer
import java.util.TimerTask

class MusicPlayer private constructor() {
    companion object {
        private var instance: MusicPlayer? = null

        fun getInstance(): MusicPlayer {
            if (instance == null) {
                instance = MusicPlayer()
            }
            return requireNotNull(instance)
        }
    }

    /**
     * công cụ để chơi nhạc
     */
    private var mediaPlayer: MediaPlayer? = null

    /**
     * để làm 1 cái đếm thời gian từng giây
     */
    private var timer: Timer? = null

    var iCompleteListener: ICompleteListener? = null

    fun stop() {
        try {
            mediaPlayer?.release()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun play(context: Context, @RawRes rawResId: Int) {
        try {
            stop()
            mediaPlayer = MediaPlayer.create(context, rawResId)
            mediaPlayer?.start()
            mediaPlayer?.setOnPreparedListener {
                Log.d("3igZeus", "setOnPreparedListener")
                iCompleteListener?.getEndDuration((mediaPlayer?.duration ?: 0) / 1000)
            }
            startTimer()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = null

        timer = Timer().apply {
            scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    Log.d("3THHT", "${Thread.currentThread()}")
                    iCompleteListener?.getCurrentPosition((mediaPlayer?.currentPosition ?: 0) / 1000)
                }
            }, 0L, 1_000L)
        }
    }


    fun pause() {
        try {
            mediaPlayer?.pause()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun resume() {
        try {
            mediaPlayer?.start()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @SuppressLint("DiscouragedApi")
    fun play(context: Context, path: String) {
        try {
            val rawResId = context.resources.getIdentifier(path, "raw", context.packageName)
            play(context, rawResId)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}