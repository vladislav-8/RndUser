package com.practicum.randomusercft.domain.usecase

import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.repository.UsersRepository
/**
 * юзкейс добавления пользователя в бд
 */
class InsertUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(usersModel: UsersModel) {
        usersRepository.insertUser(usersModel)
    }
}