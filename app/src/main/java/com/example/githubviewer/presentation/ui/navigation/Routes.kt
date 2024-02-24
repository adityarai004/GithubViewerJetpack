package com.example.githubviewer.presentation.ui.navigation

sealed class Routes(val route: String) {
    object Follower : Routes("followers/{userId}")
}