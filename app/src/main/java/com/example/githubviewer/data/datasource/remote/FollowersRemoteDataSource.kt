package com.example.githubviewer.data.datasource.remote

import com.example.githubviewer.data.models.FollowerDTO
import retrofit2.Response

interface FollowersRemoteDataSource {
    suspend fun getFollowersList(user: String, page: Int) : Response<List<FollowerDTO>>

}