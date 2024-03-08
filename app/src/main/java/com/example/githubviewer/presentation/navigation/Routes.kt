package com.example.githubviewer.presentation.navigation

sealed class Routes(val route: String) {
    object Follower : Routes("followers/{userId}")
}