package com.example.githubviewer.ui.screens.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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