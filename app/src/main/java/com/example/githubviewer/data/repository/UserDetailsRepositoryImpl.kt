package com.example.githubviewer.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.githubviewer.common.Resource
import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.data.models.mappers.toUserDetail
import com.example.githubviewer.domain.model.UserDetail
import com.example.githubviewer.domain.repository.UserDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(private val apiService: APIService): UserDetailsRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getUserDetails(username: String): Flow<Resource<UserDetail>> {
        return flow {
            emit(Resource.Loading())
            try{
                val response = apiService.getUser(username)
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val userDetail = response.body()!!.toUserDetail()
                        emit(Resource.Success(userDetail))
                    } else {
                        emit(Resource.Error(response.errorBody()?.string()))
                    }
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: HttpException){
                emit(Resource.Error(e.message))
            } catch (e: Exception){
                emit(Resource.Error(e.message))
            }
        }
    }
}