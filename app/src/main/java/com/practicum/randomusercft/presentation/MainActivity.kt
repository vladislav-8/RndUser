package com.practicum.randomusercft.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.practicum.randomusercft.common.navigation.Navigation
import com.practicum.randomusercft.presentation.theme.RandomUserCFTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserCFTTheme {
              Navigation()
            }
        }
    }
}
