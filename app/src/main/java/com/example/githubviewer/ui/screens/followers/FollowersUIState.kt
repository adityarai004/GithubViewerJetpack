package com.example.githubviewer.ui.screens.followers

data class FollowersUIState(
    var query: String = "",
    var isActive: Boolean = false,
    var page: Int = 1,
    var currentUsername: String = ""
)