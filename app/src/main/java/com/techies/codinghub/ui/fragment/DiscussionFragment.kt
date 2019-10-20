package com.techies.codinghub.ui.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.techies.codinghub.R
import com.techies.codinghub.adapter.DiscussionAdapter
import kotlinx.android.synthetic.main.discussion_card.*
import kotlinx.android.synthetic.main.fragment_discussion.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DiscussionFragment : Fragment() {
    private val discussions: MutableList<String> = ArrayList()


    private lateinit var discussionAdapter: DiscussionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_discussion, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discussionAdapter= DiscussionAdapter(discussions)

            Log.d("edt",comment.text.toString())
        discussion_recycler.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=discussionAdapter

        }
        btn_send.setOnClickListener {
            Log.d("size",discussions.size.toString())
            discussions.add(discussions.size,comment.text.toString())
            discussionAdapter.notifyDataSetChanged()
        }
        Log.d("size",discussions.toString())
    }

}
