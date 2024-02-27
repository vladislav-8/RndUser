package com.practicum.randomusercft.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.R
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.presentation.MainActivityViewModel
import kotlinx.coroutines.launch

/**
 * скрин, показывающий список рандом юзеров
 */
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

    if (isHistoryVisible.value) {
        isVisible.value = false
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
            shape = RoundedCornerShape(50),
            border = BorderStroke(2.dp, Color.Red),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = stringResource(id = R.string.history_list))
        }
        AnimatedVisibility(visible = isHistoryVisible.value) {

            Box {
                if (historyList.isEmpty()) {
                    Text(stringResource(id = R.string.list_is_empty))
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .clipToBounds()
                    ) {
                        itemsIndexed(historyList) { index, item ->
                            val user = historyList[index]
                            UserCard(
                                user = user,
                                onClick = { onClick(user) },
                                onLongClick = {
                                    viewModel.deleteUser(user)
                                }
                            )
                        }
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
            Text(text = stringResource(id = R.string.open_list_button))
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
                        UserCard(
                            user = user,
                            onClick = { onClick(user) }
                        )
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


