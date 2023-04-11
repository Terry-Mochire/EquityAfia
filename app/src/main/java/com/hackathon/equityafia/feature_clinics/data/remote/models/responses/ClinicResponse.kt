package com.hackathon.equityafia.feature_clinics.data.remote.models.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class ClinicResponse : ArrayList<ClinicResponseItem>()


@Parcelize
data class ClinicResponseItem(
    val fields: Fields,
    val model: String,
    val pk: Int
): Parcelable
