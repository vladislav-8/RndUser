package com.practicum.randomusercft.domain

import com.practicum.randomusercft.data.models.UsersModel
/**
 * дата класс для показа списка истории
 */
data class HistoryUsersState(
    val users: List<UsersModel> = emptyList()
)