package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class AllServicesResponse : ArrayList<AllServicesResponseItem>()

data class AllServicesResponseItem(
    val fields: ServicesFields,
    val model: String,
    val pk: Int
)

data class ServicesFields(
    val description: String,
    val name: String
)