package com.rushikeshb.pullreq.model

data class PullRequest(
    val title: String?,
    val created_at: String?,
    val closed_at: String?,
    val user: User
)
