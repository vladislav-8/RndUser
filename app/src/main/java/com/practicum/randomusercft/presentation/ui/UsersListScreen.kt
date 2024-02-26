package com.practicum.randomusercft.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.presentation.MainActivityViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersListScreen(
    users: List<UsersModel>,
    onClick: (UsersModel) -> Unit
) {
    val viewModel = viewModel<MainActivityViewModel>()
    val historyList = viewModel.state.value.users

    val isVisible = remember {
        mutableStateOf(true)
    }
    val isHistoryVisible = remember {
        mutableStateOf(false)
    }

    var isRefreshing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            coroutineScope.launch {
                viewModel.loadUsers()
                isRefreshing = false
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {
                isHistoryVisible.value = !isHistoryVisible.value
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier.border(2.dp, color = Color.Red)
        ) {
            Text(text = "История просмотров пользователей")
        }
        AnimatedVisibility(visible = isHistoryVisible.value) {

            Box {
                LazyColumn(
                    modifier = Modifier
                        .clipToBounds()
                ) {
                    itemsIndexed(historyList) {index, item ->
                        val user = historyList[index]
                        UserCard(user = user, onClick = { onClick(user) })
                    }
                }
            }
        }

        Button(
            onClick = {
                isVisible.value = !isVisible.value
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "нажми на меня, чтобы показать список")
        }
        AnimatedVisibility(visible = isVisible.value) {

            Box {
                LazyColumn(
                    modifier = Modifier
                        .pullRefresh(pullRefreshState)
                        .clipToBounds()
                ) {
                    itemsIndexed(users) { index, item ->
                        val user = users[index]
                        UserCard(user = user, onClick = { onClick(user) })
                    }
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}
