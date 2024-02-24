package com.example.githubviewer.domain.model

import com.google.gson.annotations.SerializedName

data class Follower(
    val avatarUrl: String,
    val login: String,
)