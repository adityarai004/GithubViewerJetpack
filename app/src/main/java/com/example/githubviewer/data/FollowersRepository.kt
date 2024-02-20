package com.example.githubviewer.data

import androidx.lifecycle.MutableLiveData
import com.example.githubviewer.api.APIService
import com.example.githubviewer.api.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FollowersRepository @Inject constructor(private val apiService: APIService){

    suspend fun getUserFollowers(userId: String,page: Int): Flow<Resource<List<Follower>>> {
        return flow {
            emit(Resource.Loading())

            val response = apiService.getUserFollowers(userId,page = page)
            if(response.isSuccessful && response.body() != null){
                emit(Resource.Success(response.body()!!))
            }else{
                emit(Resource.Error(response.errorBody().toString(), emptyList()))
            }
        }.catch {e->
            emit(Resource.Error(e.message))
        }
    }

}