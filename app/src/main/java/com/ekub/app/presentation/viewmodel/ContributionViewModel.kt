package com.ekub.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekub.app.domain.model.Contribution
import com.ekub.app.domain.usecase.GetContributionsByGroupIdUseCase
import com.ekub.app.domain.usecase.GetContributionsByMemberIdUseCase
import com.ekub.app.domain.usecase.SaveContributionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContributionUIState(
    val contributions: List<Contribution> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalAmount: Double = 0.0
)

@HiltViewModel
class ContributionViewModel @Inject constructor(
    private val getContributionsByGroupIdUseCase: GetContributionsByGroupIdUseCase,
    private val getContributionsByMemberIdUseCase: GetContributionsByMemberIdUseCase,
    private val saveContributionUseCase: SaveContributionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContributionUIState())
    val uiState: StateFlow<ContributionUIState> = _uiState.asStateFlow()

    fun loadContributionsByGroup(groupId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getContributionsByGroupIdUseCase(groupId).collect { contributions ->
                    _uiState.value = _uiState.value.copy(
                        contributions = contributions,
                        totalAmount = contributions.sumOf { it.amount },
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

    fun loadContributionsByMember(memberId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                getContributionsByMemberIdUseCase(memberId).collect { contributions ->
                    _uiState.value = _uiState.value.copy(
                        contributions = contributions,
                        totalAmount = contributions.sumOf { it.amount },
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

    fun saveContribution(contribution: Contribution) {
        viewModelScope.launch {
            try {
                saveContributionUseCase(contribution)
                // Reload contributions after saving
                loadContributionsByGroup(contribution.groupId)
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
