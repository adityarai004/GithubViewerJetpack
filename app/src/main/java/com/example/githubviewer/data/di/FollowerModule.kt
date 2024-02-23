package com.example.githubviewer.data.di

import androidx.compose.runtime.Stable
import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.data.datasource.remote.BaseRemoteDataSource
import com.example.githubviewer.data.datasource.remote.FollowersRemoteDataSource
import com.example.githubviewer.data.datasource.remote.impl.FollowersRemoteDataSourceImpl
import com.example.githubviewer.data.repository.FollowersRepositoryImpl
import com.example.githubviewer.domain.repository.FollowersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Stable
@Module
@InstallIn(ViewModelComponent::class)
class FollowerModule {

    @Provides
    fun provideFollowersRemoteDataSource(
        apiService: APIService
    ):FollowersRemoteDataSource = FollowersRemoteDataSourceImpl(apiService)

    @Provides
    fun provideFollowerRepository(
        followerRemoteDataSource: FollowersRemoteDataSource
    ): FollowersRepository = FollowersRepositoryImpl(followerRemoteDataSource)
}