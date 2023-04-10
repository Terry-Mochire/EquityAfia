package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class AllCountiesResponse : ArrayList<AllCountiesResponseItem>()

data class AllCountiesResponseItem(
    val fields: CountyFields,
    val model: String,
    val pk: Int
)

data class CountyFields(
    val country: Int,
    val name: String
)