package com.techies.codinghub.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techies.codinghub.PurchaseActivity
import com.techies.codinghub.R
import com.techies.codinghub.adapter.ViewPagerAdapter
import com.techies.codinghub.ui.fragment.DiscussionFragment
import com.techies.codinghub.ui.fragment.InstructorFragment
import com.techies.codinghub.ui.fragment.VideoFragment

import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.content_course.*

class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawable=intent.getIntExtra("drawable",0)
        val coursename=intent.getStringExtra("coursename")
        val level=intent.getStringExtra("level")
        icon.setImageResource(drawable)

        course_name.text=coursename
        course_level.text=level

        setUpViewPager()

        btn_buy.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DiscussionFragment(), "Discussions")
        adapter.addFragment(VideoFragment(), "Videos")
        adapter.addFragment(InstructorFragment(), "Instructor Info")
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }
}
