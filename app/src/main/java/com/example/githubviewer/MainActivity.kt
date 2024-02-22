package com.example.githubviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.githubviewer.navigation.BottomNavItem
import com.example.githubviewer.navigation.BottomNavigationBar
import com.example.githubviewer.ui.theme.GithubViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val bottomNavigationItems = listOf(
        BottomNavItem.Search,
        BottomNavItem.Favorites,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    BottomNavigationBar(
                        scaffoldState = scaffoldState,
                        navController = rememberNavController(),
                        items = bottomNavigationItems
                    )
                }
            }
        }
    }
}

//@Composable
//fun MainScreen(){
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController, bottomNavigationItems) }
//    ) {padding ->
//
//        NavigationGraph(navController = navController)
//    }
//}