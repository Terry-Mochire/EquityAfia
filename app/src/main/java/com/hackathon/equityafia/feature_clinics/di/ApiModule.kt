package com.hackathon.equityafia.feature_clinics.di

import com.google.gson.GsonBuilder
import com.hackathon.equityafia.feature_clinics.data.remote.api.ApiService
import com.hackathon.equityafia.feature_clinics.data.repository.ApiRepository
import com.hackathon.equityafia.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request().newBuilder()
                    .addHeader("Cookie",  "csrftoken=qLKrwnPRzrKunPF6tE9zaRddPzlaPT7d; messages=.eJyLjlaKj88qzs-Lz00tLk5MT1XSMdAxMdBRCshJTSxOVShOzUlNLlFIBDKKyjKTUxUS81IUknMy8zKTlXSUlGJ1RvWP6h_VT7b-WACcnyhq:1pmAMW:-TiQV29ec0d4Xca-pPXfRdJP4bOvl2WpDhgqRTiPjUY; sessionid=t2f8l1y58pvp3r0fbhkzhfisb70ms0dk")
                    .build()
                chain.proceed(original)
            }
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesAPIService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesAPIRepository(apiService: ApiService) = ApiRepository(apiService = apiService)

}