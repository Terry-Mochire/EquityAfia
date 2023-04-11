package com.hackathon.equityafia.feature_navigation

sealed class Screens (val route: String, val title: String) {


    object SignInScreen : Screens("signin", "Sign In")
    object SignUpScreen : Screens("signup", "Sign Up")
    object HomeScreen : Screens("home", "Home")
    object ProfileScreen : Screens("profile", "Profile")
    object ClinicDetailsScreen : Screens("clinics_details", "Clinics Details")
    object LocationsFormScreen : Screens("location_form", "Locations Form")
    object AppointmentFormScreen : Screens("appointment_form", "Appointment Form")
    object AppointmentDetailsScreen : Screens("appointment_details", "Appointment Details")
}
