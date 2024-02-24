package com.example.githubviewer.presentation.ui.screens.search

data class SearchUIState(
    var showDialog:Boolean = false,
    var shouldNavigate:Boolean = false,
    var userId: String = ""
)