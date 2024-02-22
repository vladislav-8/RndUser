package com.practicum.randomusercft.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.UiState
import com.practicum.randomusercft.presentation.MainActivityViewModel
import com.practicum.randomusercft.presentation.theme.RandomUserCFTTheme

@Composable
fun ContentScreen(

) {
    val viewModel = viewModel<MainActivityViewModel>()
    val state = viewModel.uiState.value

    when (state) {
        UiState.START -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { viewModel.loadUsers() })
                { Text("START") }
            }
        }

        UiState.LOADING -> {
            Loading()
        }

        is UiState.FAILURE -> {
            ErrorOrEmpty()
        }

        is UiState.SUCCESS -> {
            val users = state.users
            UserListScreen(users)
        }
    }
}

@Composable
fun Loading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorOrEmpty() {
    val viewModel = viewModel<MainActivityViewModel>()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Something went wrong...", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { viewModel.loadUsers() })
        { Text("TRY AGAIN") }
    }

}

@Composable
fun UserListScreen(users: List<UsersModel>) {

    val isVisible = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(
            onClick = {
                isVisible.value = !isVisible.value
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "нажми на меня чтобы получить список")
        }
        AnimatedVisibility(visible = isVisible.value) {

            LazyColumn() {
                itemsIndexed(users) { index, item ->
                    UserCard(text = users[index].fullName)
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    RandomUserCFTTheme {
        ContentScreen()
    }
}