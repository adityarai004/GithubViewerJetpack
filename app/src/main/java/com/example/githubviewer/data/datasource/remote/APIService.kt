package com.example.githubviewer.data.datasource.remote

import com.example.githubviewer.data.models.FollowerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("{user}/followers")
    suspend fun getUserFollowers(@Path("user") user: String,@Query("per_page") perPage: Int = 50,@Query("page") page: Int): Response<List<FollowerDTO>>

}