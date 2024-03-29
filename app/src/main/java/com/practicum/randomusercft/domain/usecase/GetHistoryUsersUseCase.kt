package com.practicum.randomusercft.domain.usecase

import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
/**
 * юзкейс получения списка пользователей из бд
 */
class GetHistoryUsersUseCase(private val usersRepository: UsersRepository) {

    operator fun invoke(): Flow<List<UsersModel>> {
        return usersRepository.getHistoryUsers()
    }
}