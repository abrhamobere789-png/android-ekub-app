package com.ekub.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekub.app.domain.model.EkubGroup
import com.ekub.app.domain.usecase.GetAllGroupsUseCase
import com.ekub.app.domain.usecase.GetActiveGroupsUseCase
import com.ekub.app.domain.usecase.GetGroupByIdUseCase
import com.ekub.app.domain.usecase.SaveGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GroupUIState(
    val groups: List<EkubGroup> = emptyList(),
    val selectedGroup: EkubGroup? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
    private val getActiveGroupsUseCase: GetActiveGroupsUseCase,
    private val getGroupByIdUseCase: GetGroupByIdUseCase,
    private val saveGroupUseCase: SaveGroupUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupUIState())
    val uiState: StateFlow<GroupUIState> = _uiState.asStateFlow()

    init {
        loadAllGroups()
    }

    fun loadAllGroups() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getAllGroupsUseCase().collect { groups ->
                    _uiState.value = _uiState.value.copy(
                        groups = groups,
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

    fun loadActiveGroups() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getActiveGroupsUseCase().collect { groups ->
                    _uiState.value = _uiState.value.copy(
                        groups = groups,
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

    fun selectGroup(groupId: String) {
        viewModelScope.launch {
            try {
                getGroupByIdUseCase(groupId).collect { group ->
                    _uiState.value = _uiState.value.copy(selectedGroup = group)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun saveGroup(group: EkubGroup) {
        viewModelScope.launch {
            try {
                saveGroupUseCase(group)
                loadAllGroups()
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
