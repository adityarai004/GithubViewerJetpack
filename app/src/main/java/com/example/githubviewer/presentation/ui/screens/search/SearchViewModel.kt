package com.example.githubviewer.presentation.ui.screens.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel: ViewModel() {

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