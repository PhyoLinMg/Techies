package com.elemental.exoplayertesting.player

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.os.Handler
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.SingleSampleMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.ui.SubtitleView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSpec


class AndroidPlayer(private var context: Context,
                    private val playerView: PlayerView,
                    private val sub_link:String?
){

    private var player : ExoPlayer? = null
    private var playbackPosition :Long = 0
    private var currentWindowIndex: Int =0
    private var playWhenReady: Boolean = true
    private lateinit var mediaDataSourceFactory:DataSource.Factory
    private var currUri:Uri? = null
    private var currUriList: Array<Uri>? = null
    private val mainHandler: Handler? = null


    private val lifecycleObserver =
        PlayerLifecycleObserver(this)

    init {
        while (context !is LifecycleOwner) {
            context = (context as ContextWrapper).baseContext
        }
        lifecycleObserver.registerLifecycle((context as LifecycleOwner).lifecycle)

    }

    //Play single video
    fun play(uri: Uri?){
        if(uri == null) return
        currUri = uri
        initPlayer()
        preparePlayer(currUri!!,"")
        hideSystemUi()
    }

    //Overloaded function to play the whole playlist
    fun play(uriList : Array<Uri>?){
        if(uriList == null) return
        currUriList = uriList
        initPlayer()
        preparePlayer(currUriList!!)
        hideSystemUi()
    }


    private fun initPlayer(){
        if(player == null){
            player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(context),
                DefaultTrackSelector(),
                DefaultLoadControl()
            )
            loadState()
            playerView.player = player
            playerView.subtitleView.visibility=View.VISIBLE
            playerView.controllerHideOnTouch=true
            playerView.controllerAutoShow=false
            playerView.controllerShowTimeoutMs=0
            playerView.useArtwork=false
        }else{
            loadState()
        }
    }

    //Build MediaSource for one video and prepare player
    private fun preparePlayer(uri: Uri,url:String){
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource, true, false)
    }

    private fun buildMediaSource(uri:Uri): MediaSource  {
        //TODO:This is for asset Video
        val dataSpec = DataSpec(uri)
        val assetDataSource = AssetDataSource(context)
        try {
            assetDataSource.open(dataSpec)
        } catch (e: AssetDataSource.AssetDataSourceException) {
            e.printStackTrace()
        }
        mediaDataSourceFactory=DataSource.Factory { assetDataSource }

        //TODO: This is for online video
         // mediaDataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, null))
//         TODO:this is for asset video
         val mediaSource:MediaSource= ExtractorMediaSource(assetDataSource.uri, mediaDataSourceFactory, DefaultExtractorsFactory(),
            mainHandler, null);

        //TODO:this is for online video
//        val mediaSource:MediaSource= ExtractorMediaSource(uri, mediaDataSourceFactory, DefaultExtractorsFactory(),
//            mainHandler, null)
      // TODO:For subtitles

        val textFormat:Format = Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP,
        Format.NO_VALUE,"hi")
        //Log.e("srt link is",srt_link);
        val uriSubtitle:Uri = Uri.parse(sub_link)
        val subtitleSource:MediaSource = SingleSampleMediaSource(uriSubtitle, mediaDataSourceFactory, textFormat, C.TIME_UNSET)
        val mergedSource= MergingMediaSource(mediaSource, subtitleSource)

        return mergedSource
    }

    //Overloaded function to build MediaSource for whole playlist and prepare player
    private fun preparePlayer(uriList: Array<Uri>){
        val mediaSource = MediaSourceBuilder().build(uriList)
        player?.prepare(mediaSource, true, false)
    }

    private fun saveState(){
        if (player != null) {
            playbackPosition = player?.currentPosition ?: 0L
            currentWindowIndex = player?.currentWindowIndex ?: 0
            playWhenReady = player?.playWhenReady ?: true
        }
    }

    private fun loadState(){
        player?.apply {
            playWhenReady = playWhenReady
            seekTo(currentWindowIndex, playbackPosition)
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            saveState()
            player?.release()
            player = null
        }
    }

    //region Handle Lifecycle
    fun start() {
        player?.playWhenReady = true
        player?.playbackState
        currUri?: play(currUri)
        currUriList?: play(currUriList)
    }

    fun resume(){
        player?.playWhenReady = true
        currUri?: play(currUri)
        currUriList?: play(currUriList)
        loadState()
    }

    fun pause() {
        player?.playWhenReady = false
        player?.playbackState
        saveState()
    }

    fun stop(){
        releasePlayer()

        player?.stop(true)
    }
    //endregion

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

}