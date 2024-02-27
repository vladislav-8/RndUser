package com.practicum.randomusercft.domain.repository

import com.practicum.randomusercft.data.models.UsersModel
import kotlinx.coroutines.flow.Flow
/**
 * основной репозиторий
 */
interface UsersRepository {

    suspend fun getAllUsers(): List<UsersModel>

    suspend fun insertUser(usersModel: UsersModel)

    suspend fun deleteUser(usersModel: UsersModel)

    fun getHistoryUsers(): Flow<List<UsersModel>>
}