package com.techies.codinghub.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elemental.exoplayertesting.player.AndroidPlayer
import com.techies.codinghub.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val videourl:String?=intent.getStringExtra("videourl")
        val srturl:String?=intent.getStringExtra("srturl");

        startPlayer(videourl,srturl)

    }

    override fun onPause() {
        super.onPause()
        Log.d("pause","Paused")
        val videourl:String?=intent.getStringExtra("videourl")
        val srturl:String?=intent.getStringExtra("srturl");
        AndroidPlayer(this,videoView,srturl).pause()
    }

    override fun onResume() {
        super.onResume()
        Log.d("resume","Resumed")
        val videourl:String?=intent.getStringExtra("videourl")
        val srturl:String?=intent.getStringExtra("srturl")
        AndroidPlayer(this,videoView,srturl).resume()
    }
    private fun startPlayer(videourl:String?,srturl:String?){


        val uri= Uri.parse(videourl)

        playVideo(uri,srturl)


    }
    private fun playVideo(uri: Uri,srturl: String?){
        //Here the videoView is
        //AndroidPlayer(this, videoView,"asset:///[DownSub.com] 9.19_ Prototypes in Javascript - p5.js Tutorial.srt").play(uri)
        AndroidPlayer(this, videoView,srturl).play(uri)
    }

    private fun playVideoList(uriList: Array<Uri>){
        AndroidPlayer(this, videoView,"jfkalsjfkasjfljf;l").play(uriList)
    }

}
