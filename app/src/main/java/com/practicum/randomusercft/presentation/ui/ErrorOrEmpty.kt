package com.practicum.randomusercft.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.R
import com.practicum.randomusercft.presentation.MainActivityViewModel

/**
 * плейсхолдер ошибки
 */
@Composable
fun ErrorOrEmpty() {
    val viewModel = viewModel<MainActivityViewModel>()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.something_went_wrong), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { viewModel.loadUsers() })
        { Text(stringResource(id = R.string.try_again)) }
    }
}