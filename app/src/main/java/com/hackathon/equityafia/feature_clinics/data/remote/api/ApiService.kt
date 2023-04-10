package com.hackathon.equityafia.feature_clinics.data.remote.api

import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Location
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    //GET CSRF TOKEN
    @GET("get_csrf_token")
    suspend fun getCsrfToken(): TokenResponse

    //Get all clinics
    @GET("clinics")
    suspend fun getAllClinics(): List<AllClinicsResponseItem>

    //get clinic by id
    @GET("clinics/{id}")
    suspend fun getClinicById(
        @Path("id") id: Int
    ): List<AllClinicsResponseItem>

    //get clinic by name
    @POST("search_by_name/{name}")
    suspend fun getClinicByName(
        @Header("X-CSRFToken") csrfToken: String?,
        @Path("name") name: String
    ): List<AllClinicsResponseItem>

    //Get clinic in a location
    @POST("search/")
    suspend fun getClinicByLocation(
        @Header("X-CSRFToken") csrfToken: String?,
        @Body location: Location
    ): ClinicResponse


    //Get all countries
    @GET("countries")
    suspend fun getAllCountries(): List<AllCountriesResponseItem>

    //Get all counties
    @GET("counties")
    suspend fun getAllCounties(): List<AllCountiesResponseItem>

    //Get all sub counties
    @GET("sub_counties")
    suspend fun getAllSubCounties(): List<AllSubCountiesResponseItem>

    //Get all sub counties in a county
    @GET("sub_county/{county_id}")
    suspend fun getAllSubCountiesInACounty(
        @Path("county_id") countyId: Int
    ): List<AllSubCountiesResponseItem>

}
