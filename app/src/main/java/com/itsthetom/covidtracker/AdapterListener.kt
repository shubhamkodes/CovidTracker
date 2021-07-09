package com.itsthetom.covidtracker

import android.widget.Adapter
import com.itsthetom.covidtracker.api.User

interface AdapterListener {
    fun setCountry(user:User)
}