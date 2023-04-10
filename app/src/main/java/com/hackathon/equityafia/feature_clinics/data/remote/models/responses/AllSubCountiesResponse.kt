package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

class AllSubCountiesResponse : ArrayList<AllSubCountiesResponseItem>()

data class AllSubCountiesResponseItem(
    val fields: SubCountyFields,
    val model: String,
    val pk: Int
)

data class SubCountyFields(
    val county: Int,
    val name: String
)