package com.hackathon.equityafia.feature_clinics.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.hackathon.equityafia.feature_clinics.data.remote.api.ApiService
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllClinicsResponseItem
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.ClinicResponseItem
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    val clinics: MutableState<List<AllClinicsResponseItem>> = mutableStateOf(emptyList())

    suspend fun getAllClinics(){
        val response = apiService.getAllClinics()
        clinics.value = response
    }


}