package com.techies.codinghub.ui.activity.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techies.codinghub.R
import com.techies.codinghub.adapter.CourseAdapter
import com.techies.codinghub.data.Course
import com.techies.codinghub.ui.activity.CourseActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),CourseAdapter.OnItemClickedListener {
    override fun onItemClicked(course: Course) {
        val intent= Intent(context!!,CourseActivity::class.java)

        intent.putExtra("coursename",course.course_name)
        intent.putExtra("level",course.level)
        intent.putExtra("drawable",course.drawable)
        startActivity(intent)
    }

    private lateinit var homeViewModel: HomeViewModel
    private val courses:MutableList<Course> = ArrayList()
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val course=Course("Android with Kotlin","Beginner",R.drawable.kotlin)
        val course1=Course("Beginner for Laravel","Beginner",R.drawable.laravel)
        val course2=Course("Kotlin with Design Patterns","Intermediate",R.drawable.kotlin)
        val course3=Course("PHP Tips and Tricks","Intermediate",R.drawable.php)
        val course4=Course("Android with Kotlin","Beginner",R.drawable.kotlin)
        val course5=Course("Beginner for Laravel","Beginner",R.drawable.laravel)
        courses.add(0,course)
        courses.add(1,course1)
        courses.add(2,course2)
        courses.add(3,course3)
        courses.add(4,course4)
        courses.add(5,course5)
        courseAdapter= CourseAdapter(courses,this)

        course_recycler.apply{
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=courseAdapter
        }

    }
}