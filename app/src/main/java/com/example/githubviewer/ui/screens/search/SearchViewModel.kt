package com.example.githubviewer.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.data.Follower
import com.example.githubviewer.data.FollowersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.githubviewer.api.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class SearchViewModel @Inject constructor(private val followersRepository: FollowersRepository) : ViewModel() {

    var enteredUserId by mutableStateOf("")
        private set

    fun updateUsernameEntered(username: String){
        enteredUserId = username
    }


}