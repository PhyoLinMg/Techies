package com.techies.codinghub.adapter


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elemental.exoplayertesting.Utils.inflate
import com.techies.codinghub.R
import com.techies.codinghub.data.Video
import kotlinx.android.synthetic.main.video_card.view.*



class VideoAdapter(private val videos:List<Video>, val listener: OnItemClickedListener):
    RecyclerView.Adapter<VideoAdapter.HomeViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent.inflate(R.layout.video_card))
    }

    override fun getItemCount()=videos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val video=videos[position]
        holder.bind(video,listener)
    }


    interface OnItemClickedListener {
        fun onItemClicked(video: Video)
    }
    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val videoName=itemView.name!!
        private val img=itemView.img!!
        fun bind(video:Video, listener: OnItemClickedListener){
            itemView.setOnClickListener{
                listener.onItemClicked(video)
            }
            videoName.text=video.name

        }


    }

    }

