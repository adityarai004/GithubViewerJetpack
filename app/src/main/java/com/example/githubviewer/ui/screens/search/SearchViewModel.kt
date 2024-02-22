package com.example.githubviewer.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
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
import kotlinx.coroutines.flow.update

@HiltViewModel
class SearchViewModel @Inject constructor(private val followersRepository: FollowersRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState

    fun updateUsernameEntered(username: String){
        _uiState.update { currentState ->
            currentState.copy(userId = username)
        }
    }

    fun checkUserIdEmpty(){
        if (_uiState.value.userId.isEmpty()){
            _uiState.update { currentState ->
                currentState.copy(showDialog = true)
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(shouldNavigate = true)
            }
        }
    }

    fun dismissDialogBox(){
        _uiState.update { currentState ->
            currentState.copy(showDialog = false)
        }
    }

    fun dismissShouldNavigate(){
        _uiState.update { currentState ->
            currentState.copy(shouldNavigate = false)
        }
    }
}