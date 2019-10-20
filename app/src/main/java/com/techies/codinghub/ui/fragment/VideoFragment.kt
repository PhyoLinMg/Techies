package com.techies.codinghub.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.techies.codinghub.R
import android.content.res.AssetManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.techies.codinghub.adapter.VideoAdapter
import com.techies.codinghub.data.Video
import com.techies.codinghub.ui.activity.PlayerActivity
import kotlinx.android.synthetic.main.fragment_video.*
import java.io.IOException
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class VideoFragment : Fragment(),VideoAdapter.OnItemClickedListener{
    override fun onItemClicked(video: Video) {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra("videourl", video.videopath)
        intent.putExtra("srturl",video.srtpath)
        startActivity(intent)
    }

    private val list: MutableList<String> = ArrayList()
    private val videolist:MutableList<Video> = ArrayList()
    private lateinit var videoAdapter: VideoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_video, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //Log.d("name",listAssetFiles("one",context!!).toString())



        val video=Video("Episode 1","asset:///Episode1.mp4","asset:///Episode1.srt")
        val video1=Video("Episode 2","asset:///Episode1.mp4","asset:///Episode1.srt")
        val video2=Video("Episode 3","asset:///Episode1.mp4","asset:///Episode1.srt")
        videolist.add(0,video)
        videolist.add(1,video1)
        videolist.add(2,video2)

        videoAdapter=VideoAdapter(videolist,this)
        Log.d("video",videolist.toString())

        video_recycler.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=videoAdapter
        }

    }
    private fun getFileName(folderName:String):MutableList<String>{

        val assetManager:AssetManager=context!!.assets
        try {
            // for assets folder add empty string
            val filelist = assetManager.list("")
            // for assets/subFolderInAssets add only subfolder name
            val filelistInSubfolder = assetManager.list(folderName)
            if (filelist == null) {
                // dir does not exist or is not a directory
            } else {
                for (i in filelistInSubfolder!!.indices) {
                    // Get filename of file or directory
                    val filename = filelistInSubfolder!![i]
                    list.add(i,filename)
                }
            }

            // if(filelistInSubfolder == null) ............

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list

    }

}
