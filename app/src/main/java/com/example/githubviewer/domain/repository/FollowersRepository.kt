package com.example.githubviewer.domain.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface FollowersRepository {
    suspend fun getAllFollowers(username: String, page: Int): Resource<List<Follower>>
    suspend fun getAllFollowersFlow(username: String,page: Int): Flow<Resource<List<Follower>>>
    suspend fun getUserDetails(username: String) : Flow<Resource<UserDetail>>
}