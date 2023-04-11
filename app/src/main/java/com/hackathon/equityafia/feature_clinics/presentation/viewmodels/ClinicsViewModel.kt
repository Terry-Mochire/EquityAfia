package com.hackathon.equityafia.feature_clinics.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllClinicsResponseItem
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllServicesResponseItem
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


    init {
       viewModelScope.launch {
              repository.getAllClinics()
       }
    }


}