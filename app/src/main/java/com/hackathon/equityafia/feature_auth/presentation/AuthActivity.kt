package com.hackathon.equityafia.feature_auth.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hackathon.equityafia.R
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

            navController = rememberAnimatedNavController()
            NavGraph(
                navController = navController
            )
            AuthState()

//            SplashScreen()

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


@Composable
fun SplashScreen() {
    val logoSize = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        logoSize.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.equity_afia_logo),
            contentDescription = null,
            modifier = Modifier
                .size(logoSize.value.dp * 200)
        )
    }
}



