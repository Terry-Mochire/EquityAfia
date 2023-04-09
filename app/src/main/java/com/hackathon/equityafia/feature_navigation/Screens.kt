package com.hackathon.equityafia.feature_navigation

sealed class Screens (val route: String, val title: String) {
    object SignInScreen : Screens("signin", "Sign In")
    object SignUpScreen : Screens("signup", "Sign Up")
    object HomeScreen : Screens("home", "Home")

}
