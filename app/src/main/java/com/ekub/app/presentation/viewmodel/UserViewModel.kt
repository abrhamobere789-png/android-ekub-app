package com.ekub.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekub.app.domain.model.User
import com.ekub.app.domain.usecase.GetCurrentUserUseCase
import com.ekub.app.domain.usecase.GetUserByIdUseCase
import com.ekub.app.domain.usecase.SaveUserUseCase
import com.ekub.app.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserUIState(
    val currentUser: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState: StateFlow<UserUIState> = _uiState.asStateFlow()

    init {
        loadCurrentUser()
    }

    fun loadCurrentUser() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getCurrentUserUseCase().collect { user ->
                    _uiState.value = _uiState.value.copy(
                        currentUser = user,
                        isLoggedIn = user != null,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            try {
                saveUserUseCase(user)
                _uiState.value = _uiState.value.copy(
                    currentUser = user,
                    isLoggedIn = true,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase()
                _uiState.value = UserUIState()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
