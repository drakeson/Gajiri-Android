package com.gajiri.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gajiri.app.ui.components.NavigationItem
import com.gajiri.app.ui.home.HomeScreen
import com.gajiri.app.ui.opportunities.OpportunitiesScreen
import com.gajiri.app.ui.profile.ProfileScreen
import androidx.compose.ui.tooling.preview.Preview
import com.gajiri.app.ui.navigation.AppNavHost
import com.gajiri.app.ui.theme.GajiriTheme

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun MainScreen(onOpportunitiesClick: () -> Unit) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Opportunities,
        NavigationItem.Profile
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(
                    onServiceSelected = { service ->
                        navController.navigate("opportunity_category_details/${service.title}")
                    }
                )
            }
            composable(NavigationItem.Opportunities.route) {
                OpportunitiesScreen { onOpportunitiesClick() }
            }
            composable(NavigationItem.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        navController.navigate("login") {
                            popUpTo("profile") { inclusive = true } // Remove Profile from the back stack
                        }
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainActivityPreview() {
//    GajiriTheme {
//        MainActivity()
//    }
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GajiriTheme {
                AppNavHost()
            }
        }
    }
}
