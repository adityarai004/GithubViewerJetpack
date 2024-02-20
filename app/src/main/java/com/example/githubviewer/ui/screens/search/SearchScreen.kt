package com.example.githubviewer.ui.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.githubviewer.R
import com.example.githubviewer.api.Resource
import com.example.githubviewer.data.Follower
import com.example.githubviewer.navigation.BottomNavItem
import com.example.githubviewer.navigation.Routes

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel(),navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(top = 69.dp)
    ) {

        TopLogo()
        Spacer(modifier = Modifier.padding(top = 40.dp))
        BigTextField(
            enteredUserId = searchViewModel.enteredUserId,
            onEnteredUserIdChanged = {
                searchViewModel.updateUsernameEntered(it)
            },
            onKeyboardDone = {
                navController.navigate(Routes.Follower.route)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TopLogo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        Image(
            painterResource(id = R.drawable.gh_logo),
            contentDescription = "GithubLogo",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun BigTextField(
    enteredUserId: String,
    onEnteredUserIdChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(28.dp)
    ) {
        OutlinedTextField(
            value = enteredUserId,
            onValueChange = onEnteredUserIdChanged,
            singleLine = true,
            shape = shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            label = {
                Text(text = "Enter username here")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .height(50.dp),
            onClick = onKeyboardDone,
            shape = shapes.large
        ) {
            Text(
                text = "Get Followers",
                fontSize = 16.sp
            )
        }


    }

}












