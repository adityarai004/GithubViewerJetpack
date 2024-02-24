package com.example.githubviewer.domain.interactors

import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.repository.FollowersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(private val followersRepository: FollowersRepository) {
//    suspend operator fun invoke(username: String, page: Int):Resource<List<Follower>>{
//        return followersRepository.getAllFollowers(username,page = page)
//    }

    suspend operator fun invoke(username: String, page: Int): Flow<Resource<List<Follower>>> {
        return followersRepository.getAllFollowersFlow(username,page = page)
    }
}