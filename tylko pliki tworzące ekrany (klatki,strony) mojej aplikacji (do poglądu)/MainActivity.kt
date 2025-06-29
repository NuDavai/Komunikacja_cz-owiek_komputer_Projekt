package com.example.projectbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.budgetapp.ui.screens.BillsManagerScreen
import com.example.budgetapp.ui.screens.PriceAnalystScreen
import com.example.budgetapp.ui.screens.ReceiptScannerScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route ?: ""

    AnimatedNavHost(
        navController = navController,
        startDestination = "login",
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable(
            route = "home/{login}",
            arguments = listOf(navArgument("login") { defaultValue = "Użytkowniku" })
        ) { backStackEntry ->
            val login = backStackEntry.arguments?.getString("login") ?: "Użytkowniku"
            HomeBudgetScreen(navController, currentRoute = "home", login = login)
        }
        composable("bills") {
            BillsManagerScreen(navController, currentRoute = "bills")
        }
        composable("prices") {
            PriceAnalystScreen(navController, currentRoute = "prices")
        }
        composable("scanner") {
            ReceiptScannerScreen(navController, currentRoute = "scanner")
        }
    }
}
