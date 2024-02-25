package com.example.githubviewer.presentation.ui.screens.followers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.githubviewer.common.Constants
import com.example.githubviewer.common.Resource
import com.example.githubviewer.data.repository.source.FollowerPagingFactory
import com.example.githubviewer.domain.interactors.GetFollowersUseCase
import com.example.githubviewer.domain.model.Follower
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<FollowersUIState> = MutableStateFlow(
        FollowersUIState()
    )
    val uiState: StateFlow<FollowersUIState> = _uiState

    var followers: Flow<PagingData<Follower>> = flow {}
    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let {
             fetchFollowers(it)
        }
    }

    private fun fetchFollowers(username: String){
        viewModelScope.launch (Dispatchers.IO){
            followers = getFollowersUseCase.invoke(username = username)
        }
    }

    fun updateSearchQuery(newSearchQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(query = newSearchQuery)
        }
    }

    fun updateActiveState(newActiveState: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isActive = newActiveState)
        }
    }

    fun updateUsername(username: String) {
        _uiState.update { currentState ->
            currentState.copy(currentUsername = username)
        }
    }
}