package com.example.githubviewer.data.models

import com.example.githubviewer.ui.models.FollowerUI

fun FollowerDTO.toFollowerUI(): FollowerUI{
    return FollowerUI(
        login = login,
        avatarUrl = avatarUrl
    )
}