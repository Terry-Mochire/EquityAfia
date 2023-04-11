package com.hackathon.equityafia.feature_clinics.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.hackathon.equityafia.feature_clinics.data.remote.api.ApiService
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllClinicsResponseItem
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllServicesResponseItem
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    val clinics: MutableState<List<AllClinicsResponseItem>> = mutableStateOf(emptyList())
    val services: MutableState<List<AllServicesResponseItem>> = mutableStateOf(emptyList())

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


}