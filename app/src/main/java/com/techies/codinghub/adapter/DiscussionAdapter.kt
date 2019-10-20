package com.techies.codinghub.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elemental.exoplayertesting.Utils.inflate
import com.techies.codinghub.R
import kotlinx.android.synthetic.main.discussion_card.view.*

class DiscussionAdapter(private val discussions:List<String>):
    RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscussionViewHolder {
        return DiscussionViewHolder(parent.inflate(R.layout.discussion_card))
    }

    override fun getItemCount()=discussions.size

    override fun onBindViewHolder(holder: DiscussionViewHolder, position: Int) {
        val discussion=discussions[position]
        holder.bind(discussion)
    }

    inner class DiscussionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val discussiontext=itemView.distext!!
        fun bind(discussion:String){

            discussiontext.text=discussion
        }


    }
}