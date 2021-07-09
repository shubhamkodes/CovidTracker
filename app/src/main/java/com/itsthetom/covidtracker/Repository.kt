package com.itsthetom.covidtracker

import com.itsthetom.covidtracker.api.ApiUtilities
import com.itsthetom.covidtracker.api.User
import retrofit2.Response

class Repository {
    suspend fun getCountriesData(): Response<List<User>>{
       return  ApiUtilities.api.getCountriesData();
    }

}