package com.hackathon.equityafia.feature_auth.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hackathon.equityafia.feature_navigation.NavGraph
import com.hackathon.equityafia.feature_navigation.Screens
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel: AuthViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            println("AuthActivity: onCreate:")

            navController = rememberAnimatedNavController()
            NavGraph(
                navController = navController
            )
            AuthState()

        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    private fun AuthState() {
        val isUserSignedOut = viewModel.getAuthState().value
        if (isUserSignedOut) {
            NavigateToSignUpScreen()
        } else {
            NavigateToHomeScreen()
        }
    }

    @Composable
    private fun NavigateToHomeScreen() {
        navController.navigate(Screens.HomeScreen.route){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }

    @Composable
    private fun NavigateToSignUpScreen() {
        navController.navigate(Screens.SignUpScreen.route){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }
}



