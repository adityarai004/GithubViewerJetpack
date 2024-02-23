package com.example.githubviewer.data.datasource.remote.impl

import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.data.datasource.remote.BaseRemoteDataSource
import com.example.githubviewer.data.datasource.remote.FollowersRemoteDataSource
import com.example.githubviewer.data.models.FollowerDTO
import retrofit2.Response
import javax.inject.Inject

class FollowersRemoteDataSourceImpl @Inject constructor(private val apiService: APIService) :
    BaseRemoteDataSource(), FollowersRemoteDataSource {
    override suspend fun getFollowersList(
        user: String,
        page: Int
    ): Response<List<FollowerDTO>> = apiService.getUserFollowers(user, page = page)

}