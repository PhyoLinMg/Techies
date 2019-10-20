package com.techies.codinghub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.techies.codinghub.adapter.CourseAdapter
import com.techies.codinghub.data.Course
import com.techies.codinghub.ui.activity.CourseActivity
import kotlinx.android.synthetic.main.fragment_home.*

class ProfileActivity : AppCompatActivity(), CourseAdapter.OnItemClickedListener {
    private val courses:MutableList<Course> = ArrayList()
    private lateinit var courseAdapter: CourseAdapter

    override fun onItemClicked(course: Course) {
        val intent= Intent(this, CourseActivity::class.java)

        intent.putExtra("coursename",course.course_name)
        intent.putExtra("level",course.level)
        intent.putExtra("drawable",course.drawable)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val course=Course("Android with Kotlin","Beginner",R.drawable.kotlin)
        val course1=Course("Beginner for Laravel","Beginner",R.drawable.laravel)
        val course2=Course("Kotlin with Design Patterns","Intermediate",R.drawable.kotlin)
        courses.add(0,course)
        courses.add(1,course1)
        courses.add(2,course2)
        courseAdapter= CourseAdapter(courses, this)

        course_recycler.apply{
            layoutManager= LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=courseAdapter
        }
    }
}
