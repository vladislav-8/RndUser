package com.practicum.randomusercft.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.UiState
import com.practicum.randomusercft.presentation.MainActivityViewModel
import com.practicum.randomusercft.presentation.theme.RandomUserCFTTheme

/**
 * стейты для показа скринов
 */

@Composable
fun ContentScreen(
    navigateToDetails: (UsersModel) -> Unit,
    viewModel: MainActivityViewModel = viewModel()
) {

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
            UsersListScreen(
                users,
                onClick = {
                navigateToDetails(it)
                viewModel.insertUser(it)
            })
        }
    }
}

@Preview
@Composable
fun Preview() {
    RandomUserCFTTheme {
        ContentScreen(navigateToDetails = {})
    }
}