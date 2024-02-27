package com.practicum.randomusercft.common.navigation
/**
 * роуты приложения
 */
sealed class Routes(
    val route: String
) {
    data object ContentScreen: Routes(route = "contentScreen")
    data object DetailsScreen: Routes(route = "detailsScreen")
}