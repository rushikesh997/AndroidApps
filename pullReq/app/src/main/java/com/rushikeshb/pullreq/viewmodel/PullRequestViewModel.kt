package com.rushikeshb.pullreq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushikeshb.pullreq.model.PullRequest
import com.rushikeshb.pullreq.model.repository.PullRequestServiceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val service: PullRequestServiceHelper,
): ViewModel() {

    private val _pullRequestList = MutableLiveData<List<PullRequest>>()
    val pullRequestList: LiveData<List<PullRequest>> get() = _pullRequestList

    init {
        getPullRequests()
    }

    private fun getPullRequests() {
        viewModelScope.launch{
            service.getPullRequests().let {
                _pullRequestList.postValue(it)
            }
        }
    }
}
