package com.hackathon.equityafia.feature_clinics.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.AllClinicsResponseItem
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ClinicsViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    var clinics: MutableState<List<AllClinicsResponseItem>> = repository.clinics

    fun getClinic(name: String){
        viewModelScope.launch {
             repository.getClinic(name)
        }
    }

    init {
       viewModelScope.launch {
              repository.getAllClinics()
       }
    }


}