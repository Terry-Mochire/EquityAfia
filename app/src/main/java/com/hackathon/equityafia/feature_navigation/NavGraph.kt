package com.hackathon.equityafia.feature_navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.hackathon.equityafia.feature_auth.presentation.*
import com.hackathon.equityafia.feature_clinics.data.remote.api.ApiService
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import com.hackathon.equityafia.feature_clinics.di.ApiModule
import com.hackathon.equityafia.feature_clinics.presentation.ClinicsViewModel
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
            val httpLoggingInterceptor = ApiModule.providesLoggingInterceptor()
            val okHttpClient = ApiModule.providesOkHttpClient(httpLoggingInterceptor)
            val apiService = ApiModule.providesAPIService(okHttpClient)
            val clinicsViewModel = ClinicsViewModel(repository = ApiRepository(apiService = apiService))

            composable(Screens.SignInScreen.route){
                SignInScreen(navController = navController, repository = authRepository ,viewModel = authViewModel)
            }
            composable(Screens.SignUpScreen.route){
                SignUpScreen(navController = navController, repository = authRepository ,viewModel = authViewModel)
            }
            composable(Screens.HomeScreen.route){
                HomeScreen(navController = navController, viewModel = authViewModel, clinicsViewModel = clinicsViewModel)
            }

            composable(Screens.ProfileScreen.route){
                ProfileScreen(navController = navController, viewModel = authViewModel)
            }

        },

    )


}