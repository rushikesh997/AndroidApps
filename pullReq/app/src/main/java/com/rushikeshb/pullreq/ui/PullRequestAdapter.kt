package com.rushikeshb.pullreq.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rushikeshb.pullreq.databinding.ListItemBinding
import com.rushikeshb.pullreq.model.PullRequest
import com.squareup.picasso.Picasso

class PullRequestAdapter(var pullRequestList: ArrayList<PullRequest>) :
    RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    inner class PullRequestViewHolder(val listItemBinding: ListItemBinding):
        RecyclerView.ViewHolder(listItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PullRequestViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return PullRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]
        holder.listItemBinding.apply {
            title.text = item.title
            user.text = item.user.login
            createdDate.text = item.created_at
            closedDate.text = item.closed_at
            Picasso.get().load(item.user.avatar_url).into(imageView)
        }
    }

    override fun getItemCount() = pullRequestList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updatePullRequestList(latestPullRequests: List<PullRequest>) {
        pullRequestList.clear()
        pullRequestList.addAll(latestPullRequests)
        notifyDataSetChanged()
    }
}
