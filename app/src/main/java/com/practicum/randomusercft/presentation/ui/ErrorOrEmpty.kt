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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.randomusercft.presentation.MainActivityViewModel

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