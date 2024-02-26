package com.practicum.randomusercft.domain

import com.practicum.randomusercft.data.models.UsersModel

data class HistoryUsersState(
    val users: List<UsersModel> = emptyList()
)