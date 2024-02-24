package com.example.githubviewer.data.models

import com.example.githubviewer.data.models.dto.FollowerDTO
import com.example.githubviewer.data.models.dto.UserDetailDTO
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
import java.text.SimpleDateFormat
import java.util.Locale

fun FollowerDTO.toFollower(): Follower {
    return Follower(
        login = login,
        avatarUrl = avatarUrl
    )
}

fun UserDetailDTO.toUserDetail(): UserDetail {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date = inputFormat.parse(createdAt)
    val outputFormat = SimpleDateFormat("MMM-yyyy", Locale.getDefault())

    return UserDetail(
        avatarUrl,
        bio,
        outputFormat.format(date),
        followers,
        following,
        htmlUrl,
        location,
        login,
        name,
        publicGists,
        publicRepos
    )
}