package com.ekub.app.presentation.navigation

sealed class NavigationRoute(val route: String) {
    object Splash : NavigationRoute("splash")
    object Login : NavigationRoute("login")
    object Register : NavigationRoute("register")
    object Home : NavigationRoute("home")
    object Groups : NavigationRoute("groups")
    object GroupDetail : NavigationRoute("group_detail/{groupId}") {
        fun createRoute(groupId: String) = "group_detail/$groupId"
    }
    object CreateGroup : NavigationRoute("create_group")
    object Members : NavigationRoute("members")
    object Contributions : NavigationRoute("contributions")
    object Transactions : NavigationRoute("transactions")
    object Profile : NavigationRoute("profile")
    object Settings : NavigationRoute("settings")
}
