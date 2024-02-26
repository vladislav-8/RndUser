package com.practicum.randomusercft.domain.usecase

import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.repository.UsersRepository

class DeleteUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(usersModel: UsersModel) {
        usersRepository.deleteUser(usersModel)
    }
}