package com.ekub.app.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekub.app.presentation.navigation.NavigationRoute
import com.ekub.app.presentation.ui.theme.PrimaryBlue
import com.ekub.app.presentation.ui.theme.SecondaryGreen
import com.ekub.app.presentation.ui.theme.BackgroundWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var isVisible by remember { mutableStateOf(false) }
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(1000)
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(Unit) {
        isVisible = true
        delay(3000)
        navController.navigate(NavigationRoute.Login.route) {
            popUpTo(NavigationRoute.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(animatedAlpha)
                .scale(animatedScale)
        ) {
            // Animated Logo Circle
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        color = PrimaryBlue,
                        shape = MaterialTheme.shapes.large
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "E",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = BackgroundWhite
                )
            }

            Text(
                text = "Digital Ekub",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier
                    .alpha(animatedAlpha)
            )

            Text(
                text = "Community Savings Made Digital",
                fontSize = 14.sp,
                color = com.ekub.app.presentation.ui.theme.TextMedium,
                modifier = Modifier
                    .alpha(animatedAlpha)
            )
        }
    }
}
