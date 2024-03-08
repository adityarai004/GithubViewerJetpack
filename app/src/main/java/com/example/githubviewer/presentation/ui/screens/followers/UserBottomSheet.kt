package com.example.githubviewer.presentation.ui.screens.followers

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.githubviewer.domain.model.UserDetail

@Composable
fun UserBottomSheetContent(userDetail: UserDetail){
    Column {
        Text(text = userDetail.login)
    }
}