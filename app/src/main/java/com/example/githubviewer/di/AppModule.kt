package com.example.githubviewer.di

import com.example.githubviewer.data.datasource.remote.APIService
import com.example.githubviewer.common.Constants.BASE_URL
import com.example.githubviewer.data.repository.FollowersRepositoryImpl
import com.example.githubviewer.data.repository.UserDetailsRepositoryImpl
import com.example.githubviewer.data.repository.source.FollowerPagingFactory
import com.example.githubviewer.domain.interactors.GetUserDetailsUseCase
import com.example.githubviewer.domain.repository.FollowersRepository
import com.example.githubviewer.domain.repository.UserDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideFollowersRepository(apiService: APIService): FollowersRepository {
        return FollowersRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePagingFactory(followersRepository: FollowersRepository): FollowerPagingFactory{
        return FollowerPagingFactory(followersRepository)
    }

    @Provides
    @Singleton
    fun provideUserDetailsRepository(apiService: APIService): UserDetailsRepository{
        return UserDetailsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserDetailsUseCase(userDetailsRepository: UserDetailsRepository) : GetUserDetailsUseCase{
        return GetUserDetailsUseCase(userDetailsRepository)
    }
}