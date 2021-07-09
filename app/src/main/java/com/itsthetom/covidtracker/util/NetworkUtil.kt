package com.itsthetom.covidtracker.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.net.ConnectException

object NetworkUtil {
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

      capabilities?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                    return true
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                    return true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->
                    return true
                else -> return false
            }

        }
        return false;
    }

}