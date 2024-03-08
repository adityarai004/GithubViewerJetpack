package com.example.githubviewer.presentation.ui.screens.followers

import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail

data class FollowersUIState(
    var query: String = "",
    var isActive: Boolean = false,
    var page: Int = 1,
    var currentUsername: String = "",
    var followers: List<Follower> = emptyList(),
    var isLoading: Boolean = true,
    var showBottomSheet: Boolean = false,
    var selectedUserName: String = "",
    var selectedUserDetail: UserDetail? = null
)