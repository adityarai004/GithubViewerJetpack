package com.example.githubviewer.data.repository.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubviewer.domain.repository.FollowersRepository
import javax.inject.Inject

class FollowerPagingFactory @Inject constructor(private val followersRepository: FollowersRepository) {

    private val NETWORK_PAGE_SIZE = 10

    fun getFollowers(username: String) =
        Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { FollowerPagingSource(followersRepository, username) }).flow
}
