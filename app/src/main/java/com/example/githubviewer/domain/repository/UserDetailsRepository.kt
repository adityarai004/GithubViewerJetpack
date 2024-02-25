package com.example.githubviewer.domain.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun getUserDetails(username: String): Flow<Resource<UserDetail>>
}