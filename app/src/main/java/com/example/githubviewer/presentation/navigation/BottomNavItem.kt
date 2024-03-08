package com.example.githubviewer.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String,val selectedIcon: ImageVector,val unselectedIcon: ImageVector,val label: String) {
    object Search: BottomNavItem("search", Icons.Filled.Search,Icons.Outlined.Search,"Search")
    object Favorites: BottomNavItem("favorites", Icons.Filled.Favorite,Icons.Outlined.FavoriteBorder,"Favorites")
}