package com.hackathon.equityafia.feature_home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {

    EquityAfiaTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(onClick = {
                viewModel.firebaseSignOut()
                navController.navigate(Screens.SignInScreen.route){
                    popUpTo(Screens.HomeScreen.route){
                        inclusive = true
                    }
                }
            }) {
                Text(text = "Sign")
            }

        }

    }

}