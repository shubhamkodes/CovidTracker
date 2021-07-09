package com.itsthetom.covidtracker.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("updated") @Expose
    var updated: Long? = null,

    @SerializedName("country")
    @Expose
    var country: String? = null,

    @SerializedName("countryInfo")
    @Expose
    val countryInfo: CountryInfo? = null,

    @SerializedName("cases")
    @Expose
    val cases: Int? = null,

    @SerializedName("todayCases")
    @Expose
    val todayCases: Int? = null,

    @SerializedName("deaths")
    @Expose
     val deaths: Int? = null,

    @SerializedName("todayDeaths")
    @Expose
     val todayDeaths: Int? = null,

    @SerializedName("recovered")
    @Expose
     val recovered: Int? = null,

    @SerializedName("todayRecovered")
    @Expose
     val todayRecovered: Int? = null,

    @SerializedName("active")
    @Expose
     val active: Int? = null,

    @SerializedName("critical")
    @Expose
     val critical: Int? = null,

    @SerializedName("casesPerOneMillion")
    @Expose
     val casesPerOneMillion: Int? = null,

    @SerializedName("deathsPerOneMillion")
    @Expose
     val deathsPerOneMillion: Double? = null,

    @SerializedName("tests")
    @Expose
     val tests: Int? = null,

    @SerializedName("testsPerOneMillion")
    @Expose
     val testsPerOneMillion: Int? = null,

    @SerializedName("population")
    @Expose
    val population: Int? = null,

    @SerializedName("continent")
    @Expose
     val continent: String? = null,

    @SerializedName("oneCasePerPeople")
    @Expose
     val oneCasePerPeople: Int? = null,

    @SerializedName("oneDeathPerPeople")
    @Expose
     val oneDeathPerPeople: Int? = null,

    @SerializedName("oneTestPerPeople")
    @Expose
     val oneTestPerPeople: Int? = null,

    @SerializedName("undefined")
    @Expose
     val undefined: Double? = null,

    @SerializedName("activePerOneMillion")
    @Expose
     val activePerOneMillion: Double? = null,

    @SerializedName("recoveredPerOneMillion")
    @Expose
     val recoveredPerOneMillion: Double? = null,

    @SerializedName("criticalPerOneMillion")
    @Expose
     val criticalPerOneMillion: Double? = null

) {
}