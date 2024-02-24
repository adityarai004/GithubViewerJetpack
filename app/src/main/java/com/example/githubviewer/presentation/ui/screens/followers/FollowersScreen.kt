package com.example.githubviewer.presentation.ui.screens.followers

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubviewer.common.Resource
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
    LazyVerticalGrid(columns = GridCells.Fixed(cols), state = lazyGridState) {
        items(itemsList) { item ->
            FollowerListItem(follower = item)
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


@Composable
@Preview(showBackground = true)
fun FollowerPrev() {
//    val fakeFollower = Follower(
//        login = "dummy_login",
//        id = 123,
//        node_id = "dummy_node_id",
//        avatar_url = "https://dummyurl.com/avatar.png",
//        gravatar_id = "dummy_gravatar_id",
//        url = "https://dummyurl.com/user",
//        html_url = "https://dummyurl.com/user/profile",
//        followers_url = "https://dummyurl.com/user/followers",
//        following_url = "https://dummyurl.com/user/following",
//        gists_url = "https://dummyurl.com/user/gists",
//        starred_url = "https://dummyurl.com/user/starred",
//        subscriptions_url = "https://dummyurl.com/user/subscriptions",
//        organizations_url = "https://dummyurl.com/user/orgs",
//        repos_url = "https://dummyurl.com/user/repos",
//        events_url = "https://dummyurl.com/user/events",
//        received_events_url = "https://dummyurl.com/user/received_events",
//        type = "dummy_type",
//        site_admin = false
//    )

//    FollowerListItem(
//        follower = fakeFollower
//    )
}