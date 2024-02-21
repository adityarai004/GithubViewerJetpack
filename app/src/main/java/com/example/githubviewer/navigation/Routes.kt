package com.example.githubviewer.navigation

sealed class Routes(val route: String) {
    object Follower : Routes("followers/{userId}")
}