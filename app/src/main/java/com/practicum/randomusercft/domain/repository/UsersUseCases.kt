package com.practicum.randomusercft.domain.repository

import com.practicum.randomusercft.domain.usecase.DeleteUserUseCase
import com.practicum.randomusercft.domain.usecase.GetHistoryUsersUseCase
import com.practicum.randomusercft.domain.usecase.InsertUserUseCase

data class UsersUseCases(
    val insertUserUseCase: InsertUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val getHistoryUsersUseCase: GetHistoryUsersUseCase
)