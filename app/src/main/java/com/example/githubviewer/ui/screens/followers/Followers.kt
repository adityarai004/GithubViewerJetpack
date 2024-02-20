package com.example.githubviewer.ui.screens.followers

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Followers(username : String) {
    Column {
        Text(text = "You are here for $username")
    }
}