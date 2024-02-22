package com.practicum.randomusercft.data.api

import com.practicum.randomusercft.domain.main_model.RandomUserModel
import com.practicum.randomusercft.utils.Constants.RESPONSE_COUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getResults(
        @Query("results") count: Int = RESPONSE_COUNT,
    ): Response<RandomUserModel>
}

