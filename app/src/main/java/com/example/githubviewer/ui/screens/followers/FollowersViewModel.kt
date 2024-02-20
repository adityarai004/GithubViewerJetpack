package com.example.githubviewer.ui.screens.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.api.Resource
import com.example.githubviewer.data.Follower
import com.example.githubviewer.data.FollowersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(private val followersRepository: FollowersRepository) : ViewModel(){


    private val _followersList : MutableStateFlow<Resource<List<Follower>>> = MutableStateFlow(Resource.Loading())
    val followersList: StateFlow<Resource<List<Follower>>> = _followersList

    fun fetchFollowers(username: String, page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            followersRepository.getUserFollowers(username,page)
                .collectLatest { followersListResponse->
                    _followersList.value = followersListResponse
                }
        }
    }


}