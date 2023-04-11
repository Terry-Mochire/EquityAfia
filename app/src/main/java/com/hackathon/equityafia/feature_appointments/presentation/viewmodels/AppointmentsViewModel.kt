package com.hackathon.equityafia.feature_appointments.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.equityafia.feature_auth.presentation.AuthRepository
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Book
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Booking
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.User
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AvailableDatesResponse
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AvailableTimesResponse
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    val authRepository = AuthRepository()
    val authViewModel = AuthViewModel(authRepository = authRepository)

    val availableDates: MutableState<AvailableDatesResponse> = repository.availableDates
    val availableTimes: MutableState<AvailableTimesResponse> = repository.availableTimes

    val bookingResponse: MutableState<String> = mutableStateOf("")

    val currentUser = authViewModel.currentUser

//    fun saveUser(user: User) {
//        if (authViewModel.currentUser != null) {
//            viewModelScope.launch {
//                repository.saveUser(user)
//            }
//        }
//    }

    fun bookAppointment(
        booking: Booking
    ) {
        viewModelScope.launch {
            repository.bookAppointment(booking)
            val user_id = authViewModel.currentUser?.uid
            println("Booking user_id: $user_id")
            println("Booking time: ${booking.time}")
            val time = booking.time
            val book = Book(time)
            val response = repository.book(user_id!!, book)
            bookingResponse.value = response.message

        }
    }

    init {
        viewModelScope.launch {
//            saveUser(
//                User(
//                    email = authViewModel.currentUser?.email!!,
//                    name = authViewModel.currentUser.displayName!!,
//                    user_id = authViewModel.currentUser.uid
//                )
//            )
            repository.getAvailableDates()
            authViewModel.currentUser?.let { repository.getAvailableTimes(it.uid) }
        }
    }


}