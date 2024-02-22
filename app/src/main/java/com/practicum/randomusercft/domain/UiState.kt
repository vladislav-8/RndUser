package com.practicum.randomusercft.domain

import com.practicum.randomusercft.data.models.UsersModel

sealed class UiState {
    data object START : UiState()
    data object LOADING : UiState()
    data class SUCCESS(val users: List<UsersModel>) : UiState()
    data class FAILURE(val message: String?) : UiState()
}