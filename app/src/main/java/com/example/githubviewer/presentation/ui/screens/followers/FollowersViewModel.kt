package com.example.githubviewer.presentation.ui.screens.followers

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.githubviewer.common.Constants
import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.interactors.GetFollowersUseCase
import com.example.githubviewer.domain.interactors.GetUserDetailsUseCase
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
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
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
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

    fun dismissBottomSheet(){
        _uiState.update { currentState ->
            currentState.copy(selectedUserName = "", selectedUserDetail = null)
        }
    }

//    fun updateSelectedUserName(selectedUserName: String) {
//        _uiState.update { currentState ->
//            currentState.copy(selectedUserName = selectedUserName)
//        }
//    }

    fun getUserInfo(selectedUserName: String){
        viewModelScope.launch(Dispatchers.IO) {
            getUserDetailsUseCase.invoke(selectedUserName).collect{
                when(it){
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        Log.d("ViewModel","An error occured ${it.message}")
                    }
                    is Resource.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(selectedUserDetail = it.data)
                        }
                    }
                }
            }
        }
    }
}