package com.example.githubviewer.presentation.ui.screens.followers

import android.util.Log
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubviewer.domain.model.Follower
import com.example.githubviewer.presentation.ui.components.GHViewSearchBar
import com.example.githubviewer.presentation.ui.components.GithubViewProfilePicture

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersScreen(
    navController: NavController,
    username: String,
    followersViewModel: FollowersViewModel = hiltViewModel()
) {
    val uiState by followersViewModel.uiState.collectAsState()

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
        }
    ) { paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            if (uiState.isLoading){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
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
            if(!uiState.isLoading && uiState.error == null){
                SpanLazyVerticalGrid(cols = 3, itemsList = uiState.followers, lazyGridState = rememberLazyGridState())
            }else{
                Log.d("TAG","Some error ${uiState.error}")
            }
        }
    }
}

@Composable
fun SpanLazyVerticalGrid(cols: Int, itemsList: List<Follower>, lazyGridState: LazyGridState) {
    val lazyListState = rememberLazyListState()
    LazyVerticalGrid(columns = GridCells.Fixed(cols), state = lazyGridState) {
        items(itemsList) { item ->
            FollowerListItem(follower = item)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        if (!lazyListState.canScrollForward) {
            Log.d("TAG","END END END")
        }
    }

}

@Composable
fun FollowerListItem(follower: Follower) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        GithubViewProfilePicture(
            imageSource = follower.avatarUrl,
            isProfilePic = true,
            modifier = Modifier
                .aspectRatio(1f) // Adjust aspect ratio if desired
                .fillMaxWidth()
                .clip(CircleShape) // Optional: Make image circular
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Text(
            text = follower.login,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}
