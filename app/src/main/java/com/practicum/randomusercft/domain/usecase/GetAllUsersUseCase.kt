package com.practicum.randomusercft.domain.usecase

import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.repository.UsersRepository

class GetAllUsersUseCase (private val usersRepository: UsersRepository) {

    suspend operator fun invoke(): List<UsersModel> = usersRepository.getAllUsers()
}