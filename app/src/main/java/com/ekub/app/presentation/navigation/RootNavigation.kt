package com.ekub.app.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ekub.app.presentation.screens.home.HomeScreen
import com.ekub.app.presentation.screens.splash.SplashScreen
import com.ekub.app.presentation.screens.login.LoginScreen

@Composable
fun RootNavigation(
    navController: NavHostController? = null
) {
    if (navController != null) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Splash.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            composable(NavigationRoute.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(NavigationRoute.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(NavigationRoute.Home.route) {
                HomeScreen(navController = navController)
            }
        }
    }
}
