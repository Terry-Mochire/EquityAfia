package com.hackathon.equityafia.feature_navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.hackathon.equityafia.feature_appointments.presentation.viewmodels.AppointmentDetailsScreen
import com.hackathon.equityafia.feature_appointments.presentation.viewmodels.AppointmentFormScreen
import com.hackathon.equityafia.feature_appointments.presentation.viewmodels.AppointmentsViewModel
import com.hackathon.equityafia.feature_auth.presentation.*
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Booking
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.ClinicResponseItem
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import com.hackathon.equityafia.feature_clinics.di.ApiModule
import com.hackathon.equityafia.feature_clinics.presentation.ClinicDetailsScreen
import com.hackathon.equityafia.feature_clinics.presentation.LocationsFormScreen
import com.hackathon.equityafia.feature_clinics.presentation.viewmodels.ClinicsViewModel
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
            val appointmentsViewModel = AppointmentsViewModel(repository = ApiRepository(apiService = apiService))

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

            composable(Screens.ClinicDetailsScreen.route){
                val clinic = navController.previousBackStackEntry?.arguments?.getParcelable<ClinicResponseItem>("clinic")
                ClinicDetailsScreen(clinic = clinic!!, clinicsViewModel = clinicsViewModel, navController = navController, viewModel = authViewModel )
            }

            composable(Screens.LocationsFormScreen.route){
                LocationsFormScreen(navController = navController, viewModel = authViewModel, clinicsViewModel = clinicsViewModel)
            }

            composable(Screens.AppointmentFormScreen.route){
                val clinic = navController.previousBackStackEntry?.arguments?.getParcelable<ClinicResponseItem>("clinic")
                println(
                    "NavControllerClinic: $clinic"
                )
                AppointmentFormScreen(navController = navController, authViewModel = authViewModel, viewModel = appointmentsViewModel, clinic = clinic!!)
            }

            composable(Screens.AppointmentDetailsScreen.route) {
                val booking = navController.previousBackStackEntry?.arguments?.getParcelable<Booking>("booking")
               AppointmentDetailsScreen()
            }

        },

    )


}