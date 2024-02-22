package com.practicum.randomusercft.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.practicum.randomusercft.presentation.theme.RandomUserCFTTheme
import com.practicum.randomusercft.presentation.ui.ContentScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserCFTTheme {
                ContentScreen()
            }
        }
    }
}