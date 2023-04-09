package com.hackathon.equityafia.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
    ) : ViewModel() {

        init {
            getAuthState()
        }

    fun getAuthState() = authRepository.getAuthState(viewModelScope)

}