package com.practicum.randomusercft.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.presentation.MainActivityViewModel
import com.practicum.randomusercft.presentation.ui.ContentScreen
import com.practicum.randomusercft.presentation.ui.DetailScreen
/**
 * навигация приложения
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.ContentScreen.route) {
        composable(Routes.ContentScreen.route) {
            val vm = hiltViewModel<MainActivityViewModel>()
            ContentScreen(
                navigateToDetails = { user ->
                    navigateToDetails(
                        navController = navController,
                        user = user
                    )
                },
                viewModel = vm
            )
        }
        composable(Routes.DetailsScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<UsersModel?>("user")
                ?.let { user ->
                    DetailScreen(
                        user = user
                    )
                }
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    user: UsersModel
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
    navController.navigate(
        route = Routes.DetailsScreen.route
    )
}