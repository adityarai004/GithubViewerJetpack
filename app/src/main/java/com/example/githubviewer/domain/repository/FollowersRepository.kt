package com.example.githubviewer.domain.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface FollowersRepository {
    suspend fun getAllFollowers(username: String, page: Int): Resource<List<Follower>>
}