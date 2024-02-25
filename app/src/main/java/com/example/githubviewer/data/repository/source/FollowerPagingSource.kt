package com.example.githubviewer.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.common.Resource
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.domain.repository.FollowersRepository
import javax.inject.Inject

class FollowerPagingSource @Inject constructor(
    private val followersRepository: FollowersRepository,
    private val username: String
) : PagingSource<Int, Follower>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Follower>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Follower> {
        val position = params.key ?: 1
        return when (val result = followersRepository.getAllFollowers(username, position)) {
            is Resource.Error -> LoadResult.Error(Exception())
            is Resource.Loading -> LoadResult.Error(Exception())
            is Resource.Success -> LoadResult.Page(
                data = result.data!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
                nextKey = if (result.data.isEmpty()) null else position + 1
            )
        }
    }
}