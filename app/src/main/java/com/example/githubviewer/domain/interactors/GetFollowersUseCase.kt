package com.example.githubviewer.domain.interactors

import androidx.paging.PagingData
import com.example.githubviewer.data.repository.source.FollowerPagingFactory
import com.example.githubviewer.domain.model.Follower
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(private val followerPagingFactory: FollowerPagingFactory) {
    suspend operator fun invoke(username: String): Flow<PagingData<Follower>> {
        return followerPagingFactory.getFollowers(username)
    }
}