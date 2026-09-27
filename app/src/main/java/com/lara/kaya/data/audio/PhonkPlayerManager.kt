package com.lara.kaya.data.audio

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.lara.kaya.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TrackInfo(
    val title: String = "DRIFT PHONK — VOL 1",
    val subtitle: String = "PHONK · 47 TRACKS · 3H 12M",
    val isPlaying: Boolean = false
)

class PhonkPlayerManager(private val context: Context) {

    private var player: ExoPlayer? = null
    private val _playerState = MutableStateFlow(TrackInfo())
    val playerState: StateFlow<TrackInfo> = _playerState.asStateFlow()

    @OptIn(UnstableApi::class)
    fun initializePlayer() {
        if (player != null) return
        
        try {
            player = ExoPlayer.Builder(context).build().apply {
                val rawResId = R.raw.phonk_drift_vol1
                val rawUri = "android.resource://${context.packageName}/$rawResId"
                val mediaItem = MediaItem.fromUri(rawUri)
                setMediaItem(mediaItem)
                repeatMode = Player.REPEAT_MODE_ALL
                prepare()

                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _playerState.value = _playerState.value.copy(isPlaying = isPlaying)
                    }
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun togglePlayPause() {
        try {
            player?.let {
                if (it.isPlaying) {
                    it.pause()
                } else {
                    it.play()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun nextTrack() {
        try {
            player?.seekTo(0)
            player?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun previousTrack() {
        try {
            player?.seekTo(0)
            player?.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        try {
            player?.release()
            player = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
