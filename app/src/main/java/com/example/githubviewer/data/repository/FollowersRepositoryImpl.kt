package com.example.githubviewer.data.repository

import com.example.githubviewer.data.datasource.remote.FollowersRemoteDataSource
import com.example.githubviewer.data.models.FollowerDTO
import com.example.githubviewer.domain.repository.FollowersRepository
import retrofit2.Response
import javax.inject.Inject

class FollowersRepositoryImpl @Inject constructor(private val followersRemoteDataSource: FollowersRemoteDataSource) : FollowersRepository {
    override suspend fun getAllFollowers(username: String, page: Int): Response<List<FollowerDTO>> =
         followersRemoteDataSource.getFollowersList(username,page)

}