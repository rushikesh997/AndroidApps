package com.rushikeshb.pullreq.model.repository

import com.rushikeshb.pullreq.model.PullRequest
import javax.inject.Inject

class PullRequestServiceHelper @Inject constructor(private val api: PullRequestService){

    suspend fun getPullRequests(): List<PullRequest> = api.getPullRequests()
}
