package com.example.githubviewer.data.repository

import com.example.githubviewer.common.Resource
import com.example.githubviewer.data.models.dto.APIError
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

open class BaseRepository {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>):Flow<Resource<T>>{
        return flow<Resource<T>>{
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if(body != null) emit(Resource.Success(body))
                else{
                    val apiError: APIError =
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    emit(Resource.Error(apiError.message))
                }
            } else{
                val apiError: APIError =
                    Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                emit(Resource.Error(apiError.message))
            }
        }
            .catch {
                emit(Resource.Error(it.message))
            }
            .onStart { emit(Resource.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}