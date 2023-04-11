package com.hackathon.equityafia.feature_clinics.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Location
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.*
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ClinicsViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    var clinics: MutableState<List<AllClinicsResponseItem>> = repository.clinics
    var services: MutableState<List<AllServicesResponseItem>> = repository.services
    var countries: MutableState<List<AllCountriesResponseItem>> = repository.countries
    var counties: MutableState<List<AllCountiesResponseItem>> = repository.counties
    var subCounties: MutableState<List<AllSubCountiesResponseItem>> = repository.subCounties

    fun getClinic(name: String){
        viewModelScope.launch {
             repository.getClinic(name)
        }
    }

    fun getServicesInAClinic(id: Int){
        viewModelScope.launch {
             repository.getSerivicesInAClinic(id)
        }
    }

    fun getSubCounties(id: Int){
        viewModelScope.launch {
            repository.getSubCounties(id)
        }
    }

    fun getAllClinicsInALocation(location: Location){
        viewModelScope.launch {
            repository.getAllClinicsInALocation(location)
        }
    }


    init {
       viewModelScope.launch {
              repository.getAllClinics()
           repository.getCountriesAndCounties()
       }
    }


}