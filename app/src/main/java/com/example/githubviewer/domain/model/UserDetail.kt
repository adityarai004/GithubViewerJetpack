package com.example.githubviewer.domain.model

data class UserDetail(
    val avatarUrl: String,
    val bio: String,
    val createdAt: String,
    val followers: Int,
    val following: Int,
    val htmlUrl: String,
    val location: String,
    val login: String,
    val name: String,
    val publicGists: Int,
    val publicRepos: Int,
)