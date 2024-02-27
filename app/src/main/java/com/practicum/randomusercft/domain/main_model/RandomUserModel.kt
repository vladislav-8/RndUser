package com.practicum.randomusercft.domain.main_model

import com.practicum.randomusercft.domain.models.Info
import com.practicum.randomusercft.domain.models.Result
/**
 * моделька для получения списка из апи
 */
data class RandomUserModel(
    val info: Info,
    val results: List<Result>
)