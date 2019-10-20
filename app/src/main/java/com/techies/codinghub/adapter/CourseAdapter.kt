package com.techies.codinghub.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elemental.exoplayertesting.Utils.inflate
import com.techies.codinghub.R
import com.techies.codinghub.data.Course
import kotlinx.android.synthetic.main.course_card.view.*


class CourseAdapter(private val courses:List<Course>,val listener: OnItemClickedListener):
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(parent.inflate(R.layout.course_card))
    }
    interface OnItemClickedListener {
        fun onItemClicked(course: Course)
    }
    override fun getItemCount()=courses.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course=courses[position]
        holder.bind(course,listener)
    }


    inner class CourseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val courseName=itemView.course_name!!
        private val courseLogo=itemView.course_logo!!
        private val level=itemView.level!!
        fun bind(course:Course,listener: OnItemClickedListener){
            itemView.setOnClickListener {
                listener.onItemClicked(course)
            }

            courseName.text=course.course_name
            level.text=course.level
            courseLogo.setImageResource(course.drawable)

        }


    }
}