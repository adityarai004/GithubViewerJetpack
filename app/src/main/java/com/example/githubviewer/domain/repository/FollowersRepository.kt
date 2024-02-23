package com.example.githubviewer.domain.repository

import com.example.githubviewer.data.models.FollowerDTO
import retrofit2.Response

interface FollowersRepository {
    suspend fun getAllFollowers(username: String, page: Int):Response<List<FollowerDTO>>
}