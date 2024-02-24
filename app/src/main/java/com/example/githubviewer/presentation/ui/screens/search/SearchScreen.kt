package com.example.githubviewer.presentation.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.githubviewer.R
import com.example.githubviewer.presentation.ui.components.BigTextField
import com.example.githubviewer.presentation.ui.components.GithubViewProfilePicture

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = viewModel(),
    navController: NavHostController
) {
    val uiState by searchViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(top = 69.dp)
    ) {
        if (uiState.showDialog) {
            CustomDialog(
                onDismissRequest = { searchViewModel.dismissDialogBox() }
            )
        }
        if (uiState.shouldNavigate) {
            navController.navigate("followers/${uiState.userId}")
            searchViewModel.dismissShouldNavigate()
        }
        GithubViewProfilePicture(R.drawable.gh_logo, modifier = Modifier.size(240.dp), isProfilePic = false)
        Spacer(modifier = Modifier.padding(top = 40.dp))
        BigTextField(
            enteredUserId = uiState.userId,
            onEnteredUserIdChanged = {
                searchViewModel.updateUsernameEntered(it)
            },
            onKeyboardDone = {
                searchViewModel.checkUserIdEmpty()
            },
            textFieldLabel = "Enter Username"
        )
    }
}





@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                "No Username",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
        },
        text = { Text("Please enter a username you are looking for.") },
        confirmButton = {
        },
        dismissButton = {
            TextButton(onDismissRequest) {
                Text("Ok".uppercase())
            }
        },
    )
}











