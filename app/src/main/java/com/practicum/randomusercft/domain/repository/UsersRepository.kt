package com.practicum.randomusercft.domain.repository

import com.practicum.randomusercft.data.models.UsersModel

interface UsersRepository {

    suspend fun getAllUsers(): List<UsersModel>
}