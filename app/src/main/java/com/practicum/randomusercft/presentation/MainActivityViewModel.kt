package com.practicum.randomusercft.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.domain.HistoryUsersState
import com.practicum.randomusercft.domain.UiState
import com.practicum.randomusercft.domain.repository.UsersUseCases
import com.practicum.randomusercft.domain.usecase.DeleteUserUseCase
import com.practicum.randomusercft.domain.usecase.GetAllUsersUseCase
import com.practicum.randomusercft.domain.usecase.GetHistoryUsersUseCase
import com.practicum.randomusercft.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    private val _uiState: MutableState<UiState> = mutableStateOf(UiState.START)
    val uiState: State<UiState> get() = _uiState

    private val _state = mutableStateOf(HistoryUsersState())
    val state: State<HistoryUsersState> = _state

    init {
        getHistoryUsers()
    }

    fun loadUsers() = viewModelScope.launch {
        _uiState.value = UiState.LOADING
        try {
            val users = withContext(Dispatchers.IO) { getAllUsersUseCase.invoke() }
            _uiState.value = UiState.SUCCESS(users)
        } catch (e: Exception) {
            _uiState.value = UiState.FAILURE(e.localizedMessage)
        }
    }

    fun insertUser(usersModel: UsersModel) {
        viewModelScope.launch(Dispatchers.IO) {
            usersUseCases.insertUserUseCase.invoke(usersModel)
        }
    }

    private fun getHistoryUsers() {
        usersUseCases.getHistoryUsersUseCase().onEach {
            _state.value = _state.value.copy(users = it.asReversed())
        }.launchIn(viewModelScope)
    }
}
