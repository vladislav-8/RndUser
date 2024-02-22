package com.practicum.randomusercft.data.impl

import com.practicum.randomusercft.data.api.RandomUserApi
import com.practicum.randomusercft.data.mappers.toModel
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val randomUserApi: RandomUserApi
) : UsersRepository {

    override suspend fun getAllUsers(): List<UsersModel> {

        val response = randomUserApi.getResults()
        val responseBody = response.body()

        if (response.isSuccessful && responseBody != null) {
            return responseBody.results.map { it.toModel() }
        }
        throw IOException(
            "Failed to retrieve user model: " +
                    response.errorBody().toString()
        )
    }
}

