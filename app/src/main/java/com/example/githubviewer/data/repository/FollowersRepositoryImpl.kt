package com.example.githubviewer.data.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.data.models.dto.APIError
import com.example.githubviewer.data.models.mappers.toFollower
import com.example.githubviewer.data.models.mappers.toUserDetail
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
import com.example.githubviewer.domain.repository.FollowersRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowersRepositoryImpl @Inject constructor(private val apiService: APIService) : FollowersRepository {
    override suspend fun getAllFollowers(username: String, page: Int): Resource<List<Follower>> {
        return try {
            val response = apiService.getUserFollowers(username, page = page)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val followerDTO = body.map {
                        it.toFollower()
                    }
                    Resource.Success(followerDTO)
                } else {
                    val apiError: APIError =
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    Resource.Error(apiError.message)
                }
            } else {
                val apiError: APIError =
                    Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                Resource.Error(apiError.message)
            }
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}