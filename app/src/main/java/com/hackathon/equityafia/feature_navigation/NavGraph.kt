package com.hackathon.equityafia.feature_navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.hackathon.equityafia.feature_auth.presentation.*
import com.hackathon.equityafia.feature_home.presentation.HomeScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
){
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.SignUpScreen.route,
        enterTransition = {EnterTransition.None },
        exitTransition = { ExitTransition.None },
        builder = {

            val authRepository = AuthRepository()
            val authViewModel = AuthViewModel(authRepository = authRepository)

            composable(Screens.SignInScreen.route){
                SignInScreen(navController = navController, repository = authRepository ,viewModel = authViewModel)
            }
            composable(Screens.SignUpScreen.route){
                SignUpScreen(navController = navController, repository = authRepository ,viewModel = authViewModel)
            }
            composable(Screens.HomeScreen.route){
                HomeScreen(navController = navController, viewModel = authViewModel)
            }

            composable(Screens.ProfileScreen.route){
                ProfileScreen(navController = navController, viewModel = authViewModel)
            }

        },

    )


}