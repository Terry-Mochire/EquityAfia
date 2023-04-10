package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class ClinicResponse : ArrayList<ClinicResponseItem>()

data class ClinicResponseItem(
    val fields: Fields,
    val model: String,
    val pk: Int
)
