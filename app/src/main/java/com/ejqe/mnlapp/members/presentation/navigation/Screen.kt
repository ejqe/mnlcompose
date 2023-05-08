package com.ejqe.mnlapp.members.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")

    object Detail: Screen(route = "detail_screen")

    object Members: Screen(route = "members_screen")

    object Oshi: Screen(route = "oshi_screen")
}
