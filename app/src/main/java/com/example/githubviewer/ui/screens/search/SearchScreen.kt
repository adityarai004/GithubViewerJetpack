package com.example.githubviewer.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.githubviewer.R

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
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
            navController.navigate("followers/${searchViewModel.enteredUserId}")
            searchViewModel.dismissShouldNavigate()
        }
        TopLogo()
        Spacer(modifier = Modifier.padding(top = 40.dp))
        BigTextField(
            enteredUserId = searchViewModel.enteredUserId,
            onEnteredUserIdChanged = {
                searchViewModel.updateUsernameEntered(it)
            },
            onKeyboardDone = {
                searchViewModel.checkUserIdEmpty()
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











