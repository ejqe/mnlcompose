package com.ejqe.mnlapp.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeNav : BottomBarScreen(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object MembersNav : BottomBarScreen(
        route = Screen.Members.route,
        title = "Members",
        icon = Icons.Default.Person
    )


    object OshiNav : BottomBarScreen(
        route = Screen.Oshi.route,
        title = "My Oshi List",
        icon = Icons.Default.Favorite
    )
}
