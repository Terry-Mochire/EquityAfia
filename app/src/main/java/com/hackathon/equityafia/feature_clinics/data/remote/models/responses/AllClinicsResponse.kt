package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class AllClinicsResponse : ArrayList<AllClinicsResponseItem>()

data class AllClinicsResponseItem(
    val fields: Fields,
    val model: String,
    val pk: Int
)

data class Fields(
    val address: String,
    val country: Int,
    val county: Int,
    val doctor_in_charge: String,
    val email: String,
    val image: String,
    val list_of_services: List<Int>,
    val name: String,
    val sub_county: Int,
    val telephone: String
)