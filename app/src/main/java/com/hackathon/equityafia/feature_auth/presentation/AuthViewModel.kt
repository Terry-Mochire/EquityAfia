package com.hackathon.equityafia.feature_auth.presentation

import android.app.Activity
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
    ) : ViewModel() {

        init {
            getAuthState()
        }

    fun getAuthState() = authRepository.getAuthState(viewModelScope)

    val currentUser = authRepository.currentUser

    fun firebaseSignUpWithEmailAndPassword(email: String, password: String, password2: String): Boolean{
        return if (validateForm(email, password, password2)) {
            viewModelScope.launch {
                authRepository.firebaseSignUpWithEmailAndPassword(email, password)
            }
            true
        } else {
            Exception("Invalid Form")
            false
        }
    }

    fun firebaseSignInWithEmailAndPassword(email: String, password: String): Boolean {
        return try {
            viewModelScope.launch {
                authRepository.firebaseSignInWithEmailAndPassword(email, password)
            }
            true
        } catch (e: Exception) {
            println("Exception $e")
            false
        }

    }

//    fun firebaseSignInWithGoogle(activity: Activity): Boolean {
//        viewModelScope.launch {
//            return@launch(authRepository.firebaseSignInWithGoogle(activity))
//        }
//        return false
//    }

    fun firebaseSignOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun validateForm(email: String, password: String, password2: String): Boolean {
        if (email.isEmpty()) {
            return false
        }
        if (password.isEmpty()) {
            return false
        }
        if (password2.isEmpty()) {
            return false
        }
        if (password != password2) {
            return false
        }

        return true

    }

    fun updateUserDetails(name: String, phone: String, email: String, password: String, password2: String): Boolean {
        return try {
            viewModelScope.launch {
                authRepository.updateUserDetails(name, phone, email, password, password2)
            }
            true
        } catch (e: Exception) {
            println("Exception $e")
            false
        }

    }


}