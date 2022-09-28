package com.rushikeshb.pullreq.model.repository

import com.rushikeshb.pullreq.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Headers

interface PullRequestService {

    @GET("repos/rushikesh997/AndroidApps/pulls?state=closed")
    suspend fun getPullRequests() : List<PullRequest>
}
