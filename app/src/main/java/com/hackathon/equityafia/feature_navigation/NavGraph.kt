package com.hackathon.equityafia.feature_navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.hackathon.equityafia.feature_auth.presentation.SignInScreen
import com.hackathon.equityafia.feature_auth.presentation.SignUpScreen
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
            composable(Screens.SignInScreen.route){
                SignInScreen(navController = navController)
            }
            composable(Screens.SignUpScreen.route){
                SignUpScreen(navController = navController)
            }
            composable(Screens.HomeScreen.route){
                HomeScreen(navController = navController)
            }
        },

    )


}