
package com.itsthetom.covidtracker.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


data class CountryInfo(
    @SerializedName("_id")
    @Expose
    var id: Int? = null,

    @SerializedName("iso2")
    @Expose
    val iso2: String? = null,

    @SerializedName("iso3")
    @Expose
    val iso3: String? = null,

    @SerializedName("lat")
    @Expose
    val lat: Float? = null,

    @SerializedName("long")
    @Expose
    val _long: Float? = null,

    @SerializedName("flag")
    @Expose
    var flag: String? = null
)


