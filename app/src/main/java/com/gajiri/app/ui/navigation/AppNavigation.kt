package com.gajiri.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gajiri.app.MainScreen
import com.gajiri.app.ui.opportunities.JobApplicationScreen
import com.gajiri.app.ui.auth.ForgotPasswordScreen
import com.gajiri.app.ui.auth.LoginScreen
import com.gajiri.app.ui.home.HomeScreen
import com.gajiri.app.ui.opportunities.OpportunitiesScreen
import com.gajiri.app.ui.opportunities.OpportunityCategoryDetailsScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onForgotPassword = {
                    navController.navigate("forgot_password")
                }
            )
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("main") {
            MainScreen(
                onOpportunitiesClick = {
                    navController.navigate("opportunities")
                }
            )
        }
        composable("home"){
            HomeScreen(
                onServiceSelected = { service ->
                    navController.navigate("opportunity_category_details/${service.title}")
                }
            )
        }
        composable("opportunities") {
            OpportunitiesScreen(
                onJobSelected = { job ->
                    navController.navigate("job_application/${job.id}")
                }
            )
        }
        composable(
            route = "job_application/{jobId}",
            arguments = listOf(navArgument("jobId") { type = NavType.StringType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            JobApplicationScreen(
                jobId = jobId,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("categories") {
            HomeScreen(
                onServiceSelected = { service ->
                    navController.navigate("opportunity_category_details/${service.title}")
                }
            )
        }
        composable(
            route = "opportunity_category_details/{serviceId}",
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            OpportunityCategoryDetailsScreen(
                serviceId = serviceId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
