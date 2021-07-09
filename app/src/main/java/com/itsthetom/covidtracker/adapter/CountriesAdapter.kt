package com.itsthetom.covidtracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itsthetom.covidtracker.AdapterListener
import com.itsthetom.covidtracker.R
import com.itsthetom.covidtracker.api.User
import com.itsthetom.covidtracker.databinding.LayoutItemCountryBinding
import java.text.NumberFormat

class CountriesAdapter(private val context: Context, private val list:List<User>,private val adapterListener: AdapterListener):RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>(){

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val user:User= list[position]

        holder.binding.tvCountryName.text=user.country
        holder.binding.tvSerialNo.text=(position+1).toString()
        holder.binding.tvCases.text= NumberFormat.getInstance().format(user.cases)
        Glide.with(context).load(user.countryInfo?.flag).into(holder.binding.ivFlagImg)
        holder.binding.cardCountry.setOnClickListener{
            adapterListener.setCountry(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.layout_item_country,parent,false);
        println("debug : viewholder created")
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class CountryViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding:LayoutItemCountryBinding= LayoutItemCountryBinding.bind(view)
    }

}