package com.example.githubviewer.domain.interactors

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.UserDetail
import com.example.githubviewer.domain.repository.FollowersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val followersRepository: FollowersRepository) {

    suspend operator fun invoke(username: String):Flow<Resource<UserDetail>>{
        return followersRepository.getUserDetails(username)
    }

}