package com.hackathon.equityafia.feature_clinics.data.remote.models.requests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Booking(
    val clinic: String,
    val day: String,
    val service: String,
    val time: String,
    val user_id: String
): Parcelable


data class Book(
    val time: String
)