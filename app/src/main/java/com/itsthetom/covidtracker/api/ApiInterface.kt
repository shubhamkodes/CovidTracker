package com.itsthetom.covidtracker.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("countries")
    suspend fun getCountriesData(): Response<List<User>>

}