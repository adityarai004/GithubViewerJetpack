package com.example.githubviewer.presentation.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.githubviewer.presentation.ui.screens.favorite.FavoriteScreen
import com.example.githubviewer.presentation.ui.screens.followers.FollowersScreen
import com.example.githubviewer.presentation.ui.screens.search.SearchScreen

@Composable
fun BottomNavigationBar(scaffoldState: ScaffoldState,navController: NavHostController, items: List<BottomNavItem>) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination

                items.forEach { navItem ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = if (isSelected) navItem.selectedIcon else navItem.unselectedIcon, contentDescription = "Icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        },
                    )
                }
            }
        }
    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Search.route,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(BottomNavItem.Search.route) {
                SearchScreen(navController = navController)
            }
            composable(BottomNavItem.Favorites.route) {
                FavoriteScreen(navController = navController)
            }
            composable(Routes.Follower.route){ navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getString("userId")
                userId?.let{
                    FollowersScreen(navController = navController, username = userId)
                }
            }
        }
    }
}

















