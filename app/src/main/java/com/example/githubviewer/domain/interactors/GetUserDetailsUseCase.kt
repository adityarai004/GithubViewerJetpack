package com.example.githubviewer.domain.interactors

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.UserDetail
import com.example.githubviewer.domain.repository.FollowersRepository
import com.example.githubviewer.domain.repository.UserDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val userDetailsRepository: UserDetailsRepository) {

    suspend operator fun invoke(username: String):Flow<Resource<UserDetail>>{
        return userDetailsRepository.getUserDetails(username)
    }

}