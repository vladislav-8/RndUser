package com.practicum.randomusercft.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.randomusercft.domain.UiState
import com.practicum.randomusercft.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState: MutableState<UiState> = mutableStateOf(UiState.START)
    val uiState: State<UiState> get() = _uiState

    fun loadUsers() = viewModelScope.launch {
        _uiState.value = UiState.LOADING
        try {
            val users = withContext(Dispatchers.IO) { getAllUsersUseCase.invoke() }
            _uiState.value = UiState.SUCCESS(users)
        } catch (e: Exception) {
            _uiState.value = UiState.FAILURE(e.localizedMessage)
        }
    }
}