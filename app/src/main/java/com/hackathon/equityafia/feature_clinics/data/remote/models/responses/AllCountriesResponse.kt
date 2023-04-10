package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class AllCountriesResponse : ArrayList<AllCountriesResponseItem>()

data class AllCountriesResponseItem(
    val fields: CountryFields,
    val model: String,
    val pk: Int
)

data class CountryFields(
    val code: String,
    val name: String
)