package com.example.githubviewer.presentation.ui.screens.followers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.presentation.components.GHViewSearchBar
import com.example.githubviewer.presentation.components.GithubViewProfilePicture
import kotlinx.coroutines.flow.Flow
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.paging.LoadState
import com.example.githubviewer.presentation.components.ErrorItem
import com.example.githubviewer.presentation.components.LoadingItem
import com.example.githubviewer.presentation.components.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersScreen(
    navController: NavController,
    username: String,
    followersViewModel: FollowersViewModel = hiltViewModel()
) {
    val uiState by followersViewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = username,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                        },
                        onClick = {
                            if (uiState.isActive) {
                                followersViewModel.updateActiveState(false)
                            } else {
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            )
        },
        ) { paddingValues ->

        if (uiState.selectedUserDetail != null) {
            ModalBottomSheet(
                onDismissRequest = { followersViewModel.dismissBottomSheet() },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (uiState.selectedUserDetail != null){
                    UserBottomSheetContent(userDetail = uiState.selectedUserDetail!!)
                }
                else{
                    Text(text = "Something went wrong")
                }
            }
        }
        Column(
            Modifier.padding(paddingValues)
        ) {
            GHViewSearchBar(
                query = uiState.query,
                onQueryChange = {
                    followersViewModel.updateSearchQuery(it)
                },
                onSearch = {},
                placeHolder = "Search here",
                active = uiState.isActive,
                onActiveChange = {
                    followersViewModel.updateActiveState(it)
                }
            )
            SpanLazyVerticalGrid(
                modifier = Modifier.padding(16.dp),
                cols = 3,
                itemsList = followersViewModel.followers,
                lazyGridState = rememberLazyGridState(),
                onItemClick = {
                    followersViewModel.getUserInfo(it)
                }
            )
        }
    }
}

@Composable
fun SpanLazyVerticalGrid(
    modifier: Modifier = Modifier,
    cols: Int,
    itemsList: Flow<PagingData<Follower>>,
    lazyGridState: LazyGridState,
    onItemClick: (String) -> Unit
) {
    val lazyFollowerItem = itemsList.collectAsLazyPagingItems()

    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(cols), state = lazyGridState) {
        items(lazyFollowerItem.itemCount) {
            FollowerListItem(follower = lazyFollowerItem[it]!!, onClick = { selectedUserName ->
                onItemClick(selectedUserName)
            })
        }

        lazyFollowerItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = "Something went wrong",
                            modifier = Modifier.fillMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = "Something went wrong",
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FollowerListItem(follower: Follower, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .clickable { onClick(follower.login) },
    ) {
        GithubViewProfilePicture(
            imageSource = follower.avatarUrl,
            isProfilePic = true,
            modifier = Modifier
                .aspectRatio(1f) // Adjust aspect ratio if desired
                .fillMaxWidth()
                .clip(CircleShape)
            // Optional: Make image circular
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Text(
            text = follower.login,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}