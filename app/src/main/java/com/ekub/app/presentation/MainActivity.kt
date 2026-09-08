package com.ekub.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ekub.app.presentation.screen.HomeScreen
import com.ekub.app.presentation.ui.theme.EkubTheme
import com.ekub.app.presentation.viewmodel.GroupViewModel
import com.ekub.app.presentation.viewmodel.UserViewModel
import com.ekub.app.presentation.viewmodel.ContributionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val groupViewModel: GroupViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val contributionViewModel: ContributionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EkubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        groupViewModel = groupViewModel,
                        userViewModel = userViewModel,
                        contributionViewModel = contributionViewModel
                    )
                }
            }
        }
    }
}
