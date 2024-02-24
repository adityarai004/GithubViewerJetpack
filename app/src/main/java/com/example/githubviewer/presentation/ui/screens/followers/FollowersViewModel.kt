package com.example.githubviewer.presentation.ui.screens.followers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.common.Constants
import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.interactors.GetFollowersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase,
    savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _uiState: MutableStateFlow<FollowersUIState> = MutableStateFlow(FollowersUIState(
        error = null
    ))
    val uiState: StateFlow<FollowersUIState> = _uiState

    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let {
            fetchFollowers(it,_uiState.value.page)
        }
    }
    fun updateSearchQuery(newSearchQuery: String){
        _uiState.update { currentState ->
            currentState.copy(query = newSearchQuery)
        }
    }

    fun updateActiveState(newActiveState: Boolean){
        _uiState.update { currentState ->
            currentState.copy(isActive = newActiveState)
        }
    }

    private fun fetchFollowers(username: String, page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            getFollowersUseCase(username,page).collect { result ->
                when(result){
                    is Resource.Success ->{
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, followers = result.data ?: emptyList())
                        }
                    }
                    is Resource.Error ->{
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, error = result.message ?: "Something went wrong")
                        }
                    }
                    is Resource.Loading ->{
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

//    fun fetchFollowers(username: String, page: Int){
//        viewModelScope.launch(Dispatchers.IO) {
//
//            when(val res = getFollowersUseCase(username,page)){
//                is Resource.Success ->{
//                        _uiState.update { currentState ->
//                            currentState.copy(isLoading = false, followers = res.data ?: emptyList())
//                        }
//                    }
//                    is Resource.Error ->{
//                        Log.d("TAG","error = ${res.message} ?: \"Something went wrong\"")
//                        _uiState.update { currentState ->
//                            currentState.copy(isLoading = false, error = res.message ?: "Something went wrong")
//                        }
//                    }
//                    is Resource.Loading ->{
//                        _uiState.update { currentState ->
//                            currentState.copy(isLoading = true)
//                        }
//                    }
//            }
//        }
//    }
    fun updateUsername(username: String) {
        _uiState.update { currentState ->
            currentState.copy(currentUsername = username)
        }
    }
}