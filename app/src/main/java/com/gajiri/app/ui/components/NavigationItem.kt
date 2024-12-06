package com.gajiri.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Opportunities : NavigationItem(
        route = "opportunities",
        title = "Opportunities",
        icon = Icons.Filled.Work
    )

    object Profile : NavigationItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Filled.Person
    )
}