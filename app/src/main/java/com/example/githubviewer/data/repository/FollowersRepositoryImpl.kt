package com.example.githubviewer.data.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.data.models.dto.FollowerDTO
import com.example.githubviewer.data.models.dto.UserDetailDTO
import com.example.githubviewer.data.models.APIError
import com.example.githubviewer.data.models.toFollower
import com.example.githubviewer.data.models.toUserDetail
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.model.UserDetail
import com.example.githubviewer.domain.repository.FollowersRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class FollowersRepositoryImpl @Inject constructor(private val apiService: APIService) : FollowersRepository {

    override suspend fun getAllFollowersFlow(
        username: String,
        page: Int
    ): Flow<Resource<List<Follower>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getUserFollowers(username, page = page)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val followers = body.map { it.toFollower() }
                        emit(Resource.Success(followers))
                    } else {
                        val apiError = Gson().fromJson(
                            response.errorBody()?.charStream(),
                            APIError::class.java
                        )
                        emit(Resource.Error(apiError.message))
                    }
                } else {
                    val apiError =
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    emit(Resource.Error(apiError.message))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message))
            }
        }.flowOn(Dispatchers.IO) // Use appropriate dispatcher for IO operations

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

    override suspend fun getUserDetails(username: String): Flow<Resource<UserDetail>> {
        return flow {
            emit(Resource.Loading())
            try{
                val response = apiService.getUser(user = username)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val userDetail = body.toUserDetail()
                        emit(Resource.Success(userDetail))
                    } else {
                        emit(Resource.Error(response.message()))
                    }
                } else {
                   emit(Resource.Error(response.message()))
                }
            }catch (e: Exception){
                emit(Resource.Error(e.message))
            }
        }
    }
}