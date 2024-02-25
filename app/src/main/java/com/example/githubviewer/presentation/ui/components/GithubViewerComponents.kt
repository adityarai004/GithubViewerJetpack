package com.example.githubviewer.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BigTextField(
    enteredUserId: String,
    onEnteredUserIdChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    textFieldLabel: String,
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
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
            ),
            label = {
                Text(text = textFieldLabel)
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
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = "Get Followers",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun GithubViewProfilePicture(
    imageSource: Any,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    imageRatio: Float = 1f, // Adjust for desired aspect ratio
    maxHeight: Dp = 120.dp,
    isProfilePic: Boolean
) {

    if (!isProfilePic) {
        // If image source is a resource ID
        Image(
            painterResource(id = imageSource as Int),
            contentDescription = "GithubLogo",
            modifier = modifier
        )
    } else if (imageSource is String) {
        // If image source is a URL
        AsyncImage(
            model = imageSource,
            contentDescription = "Image",
            modifier = Modifier
                .size(120.dp) // Adjust size as needed
                .clip(CircleShape) // Clip the image to a circle)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GHViewSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeHolder: String,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        placeholder = {
            Text(text = placeHolder)
        },
        modifier = modifier.fillMaxWidth()
    ) {
    }
}


@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingItem() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}
