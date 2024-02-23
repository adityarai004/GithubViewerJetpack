package com.example.githubviewer.data.datasource.remote

import com.example.githubviewer.api.Resource
import com.example.githubviewer.data.models.APIError
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>):Flow<Resource<T>>{
        return flow<Resource<T>>{
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if(body != null) emit(Resource.Success(body))
                else{
                    val apiError: APIError =
                        Gson().fromJson(response.errorBody()?.charStream(),APIError::class.java)
                    emit(Resource.Error(apiError))
                }
            } else{
                val apiError: APIError =
                    Gson().fromJson(response.errorBody()?.charStream(),APIError::class.java)
                emit(Resource.Error(apiError))
            }
        }
            .catch {
                emit(Resource.Error(APIError(-1,it.message ?: it.toString())))
            }
            .onStart { emit(Resource.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}