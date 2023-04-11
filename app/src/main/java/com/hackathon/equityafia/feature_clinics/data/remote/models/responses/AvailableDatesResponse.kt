package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

data class AvailableDatesResponse(
    val dates: List<String>
)

data class AvailableTimesResponse(
    val hours: List<String>
)