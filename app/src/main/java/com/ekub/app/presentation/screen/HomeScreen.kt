package com.ekub.app.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ekub.app.domain.model.EkubGroup
import com.ekub.app.presentation.viewmodel.ContributionViewModel
import com.ekub.app.presentation.viewmodel.GroupViewModel
import com.ekub.app.presentation.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    groupViewModel: GroupViewModel,
    userViewModel: UserViewModel,
    contributionViewModel: ContributionViewModel
) {
    val groupState = groupViewModel.uiState.collectAsState()
    val userState = userViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            groupState.value.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            groupState.value.error != null -> {
                ErrorScreen(
                    error = groupState.value.error ?: "Unknown error",
                    onRetry = { groupViewModel.loadAllGroups() }
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Header
                    Text(
                        text = "Welcome to Ekub",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // User Info
                    if (userState.value.isLoggedIn) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "User: ${userState.value.currentUser?.name ?: "Unknown"}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "Email: ${userState.value.currentUser?.email ?: "Unknown"}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    // Groups List
                    Text(
                        text = "Your Groups (${groupState.value.groups.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LazyColumn {
                        items(groupState.value.groups) { group ->
                            GroupCard(
                                group = group,
                                onGroupSelected = { groupViewModel.selectGroup(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupCard(
    group: EkubGroup,
    onGroupSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = group.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Members: ${group.totalMembers} | Total Savings: ${group.totalSavings} ${group.currency}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = { onGroupSelected(group.id) },
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text("View Details")
            }
        }
    }
}

@Composable
fun ErrorScreen(
    error: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Error",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = onRetry,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Retry")
            }
        }
    }
}
