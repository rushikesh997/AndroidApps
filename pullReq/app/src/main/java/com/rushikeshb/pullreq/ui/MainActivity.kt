package com.rushikeshb.pullreq.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rushikeshb.pullreq.databinding.ActivityMainBinding
import com.rushikeshb.pullreq.viewmodel.PullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pullRequestViewModel: PullRequestViewModel by viewModels()
    private val pullRequestAdapter = PullRequestAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pullReqRv = binding.pullReqRv
        pullReqRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pullRequestAdapter
            addItemDecoration( DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
            )
        }

        observePullRequestUpdates()
    }

    private fun observePullRequestUpdates() {
        pullRequestViewModel.pullRequestList.observe(this) {
            pullRequestAdapter.updatePullRequestList(it)
        }
    }
}
