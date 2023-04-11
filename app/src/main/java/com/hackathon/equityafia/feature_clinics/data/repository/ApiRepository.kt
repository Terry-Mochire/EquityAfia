package com.hackathon.equityafia.feature_clinics.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.hackathon.equityafia.feature_clinics.data.remote.api.ApiService
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Book
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Booking
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Location
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.User
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.*
import javax.inject.Inject
import kotlin.text.Typography.times

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    val clinics: MutableState<List<AllClinicsResponseItem>> = mutableStateOf(emptyList())
    val services: MutableState<List<AllServicesResponseItem>> = mutableStateOf(emptyList())
    val countries: MutableState<List<AllCountriesResponseItem>> = mutableStateOf(emptyList())
    val counties: MutableState<List<AllCountiesResponseItem>> = mutableStateOf(emptyList())
    val subCounties: MutableState<List<AllSubCountiesResponseItem>> = mutableStateOf(emptyList())

    val availableDates: MutableState<AvailableDatesResponse> = mutableStateOf(AvailableDatesResponse(emptyList()))
    val availableTimes: MutableState<AvailableTimesResponse> = mutableStateOf(AvailableTimesResponse(emptyList()))

    private var token:String? = null

    private suspend fun getToken(): String {
        if (this.token == null) {
            this.token = apiService.getCsrfToken().csrf_token
        }
        return this.token!!
    }


    suspend fun getAllClinics(){
        val response = apiService.getAllClinics()
        clinics.value = response
    }

    suspend fun getClinic(name: String) {
       val response = apiService.getClinicByName(getToken(), name)
        clinics.value = response
    }

    suspend fun getSerivicesInAClinic(id: Int) {
       val response = apiService.getAllServicesInAClinic(id)
        services.value = response
    }

    suspend fun getCountriesAndCounties() {
       val countries = apiService.getAllCountries()
        val counties = apiService.getAllCounties()
        this.countries.value = countries
        this.counties.value = counties
    }

    suspend fun getSubCounties(id: Int) {
       val subCounties = apiService.getAllSubCountiesInACounty(id)
        this.subCounties.value = subCounties
    }


    suspend fun getAllClinicsInALocation(location: Location){
        val response = apiService.getClinicByLocation(getToken(), location)
        clinics.value = response
    }


    suspend fun saveUser(user: User) {
        apiService.saveUser(getToken(), user)
    }

    // Appointments
    suspend fun getAvailableDates() {
        val response = apiService.getAvailableDates()
        availableDates.value = response
    }

    suspend fun getAvailableTimes(user_ID: String) {
        val response = apiService.getAvailableTimes(user_ID)
        availableTimes.value = response
    }

    suspend fun bookAppointment(booking: Booking): BookAppointmentResponse {
       return apiService.bookAppointment(getToken(),booking)
    }

    suspend fun book(user_id:String, book: Book): BookAppointmentResponse{
        return apiService.book(getToken(), user_id,  book)
    }





}