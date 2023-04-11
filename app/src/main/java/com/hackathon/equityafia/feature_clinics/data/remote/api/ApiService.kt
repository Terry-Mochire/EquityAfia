package com.hackathon.equityafia.feature_clinics.data.remote.api

import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Book
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Booking
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Location
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.User
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.*
import retrofit2.http.*

interface ApiService {


    //GET CSRF TOKEN
    @GET("get_csrf_token")
    suspend fun getCsrfToken(): TokenResponse

    //SAVE USER TO DB
    @POST("save_user")
    suspend fun saveUser(
        @Header("X-CSRFToken") csrfToken: String?,
        @Body user: User
    ): String

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
    ): List<AllClinicsResponseItem>


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

    //Get all services
    @GET("services")
    suspend fun getAllServices(): List<AllServicesResponseItem>

    //Get all services in a clinic
    @GET("services_in_clinic/{clinic_id}")
    suspend fun getAllServicesInAClinic(
        @Path("clinic_id") clinicId: Int
    ): List<AllServicesResponseItem>


    // Get available dates
    @GET("booking")
    suspend fun getAvailableDates(): AvailableDatesResponse

    // Get available times
    @GET("booking_submit/{user_id}")
    suspend fun getAvailableTimes(
        @Path("user_id") userId: String,
    ): AvailableTimesResponse

    // Book an appointment
    @POST("booking")
    suspend fun bookAppointment(
        @Header("X-CSRFToken") csrfToken: String?,
        @Body booking: Booking
    ): BookAppointmentResponse

    @POST("booking_submit/{user_id}")
    suspend fun book(
        @Header("X-CSRFToken") csrfToken: String?,
        @Path("user_id") userId: String,
        @Body book: Book
    ): BookAppointmentResponse


}
