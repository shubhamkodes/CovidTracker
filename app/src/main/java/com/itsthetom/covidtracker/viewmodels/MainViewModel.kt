package com.itsthetom.covidtracker.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsthetom.covidtracker.Repository
import com.itsthetom.covidtracker.api.User
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val repository: Repository):ViewModel() {
    private lateinit var countriesData:List<User>
    private lateinit var currentCountry:MutableLiveData<User>

    init {
        countriesData= listOf()
        currentCountry= MutableLiveData()
         loadData()
    }

    public fun loadData(){
        viewModelScope.launch {
            val response:Response<List<User>> =repository.getCountriesData();
            if (response.isSuccessful){
                response.body()?.let {
                    countriesData=it

                    countriesData.forEach{
                        if (it.country.equals("India")){
                            currentCountry.value=it
                            return@forEach
                        }
                    }
                }
            }else{
                println("debug: ${response.errorBody().toString()}")
            }
        }
    }

     fun setCurrentCountry(user:User){
       currentCountry.value=user
    }

   fun getCurrentCountryDetail():LiveData<User>{
        return currentCountry;
    }

    fun getAllCountriesData():List<User>{
        return countriesData
    }



}