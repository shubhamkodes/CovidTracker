package com.itsthetom.covidtracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.itsthetom.covidtracker.adapter.CountriesAdapter
import com.itsthetom.covidtracker.api.User
import com.itsthetom.covidtracker.databinding.ActivityMainBinding
import com.itsthetom.covidtracker.util.NetworkUtil
import com.itsthetom.covidtracker.viewmodels.MainViewModel
import com.itsthetom.covidtracker.viewmodels.MainViewModelFactory
import kotlinx.coroutines.*
import org.eazegraph.lib.models.PieModel
import org.ocpsoft.prettytime.PrettyTime
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity(),AdapterListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var dialog:BottomSheetDialog
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startShimmerEffect()
        val repository=Repository()
        val factory= MainViewModelFactory(repository);
        viewModel=ViewModelProvider(this,factory).get(MainViewModel::class.java);


//        val intent= Intent(this,CountriesActivity::class.java)
        binding.countryName.setOnClickListener{
           openBottomSheetDialog();
        }
        if (NetworkUtil.isOnline(this)){
            loadData()
        }else{
            Toast.makeText(this,"Turn on Internet!!",Toast.LENGTH_LONG).show();
            CoroutineScope(Dispatchers.IO).launch {
              while (!NetworkUtil.isOnline(this@MainActivity)){
                  delay(2000)
              }
                withContext(Dispatchers.Main) {
                    loadData();
                }
            }
        }
    }

    private fun loadData() {
        viewModel.getCurrentCountryDetail().observe(this, Observer {
            setData(it)
        })
    }



    @SuppressLint("SetTextI18n")
    private  fun setData(user:User){

                    binding.countryName.text=user.country

                    binding.tvTotalConfirm.setText(NumberFormat.getInstance().format(user.cases))
                    binding.tvTodayConfirm.setText(
                        "( +" + NumberFormat.getInstance().format(user.todayCases) + " )"
                    )

                    binding.tvTotalDeath.setText(NumberFormat.getInstance().format(user.deaths))
                    binding.tvTodayDeath.setText(
                        "( +" + NumberFormat.getInstance().format(user.todayDeaths) + " )"
                    )

                    binding.tvTotalRecovered.setText(
                        NumberFormat.getInstance().format(user.recovered)
                    )
                    binding.tvTodayRecovered.setText(
                        "( +" + NumberFormat.getInstance().format(user.todayRecovered) + " )"
                    )

                    binding.tvTotalActive.setText(NumberFormat.getInstance().format(user.active))
                    binding.tvTotalActive.setText(NumberFormat.getInstance().format(user.active))

                    binding.tvTotalTest.setText(NumberFormat.getInstance().format(user.tests));

                    binding.piechart.addPieSlice(user.cases?.let { it1 ->
                        PieModel(
                            "ConfirmCases",it1.toFloat(), ContextCompat.getColor(applicationContext,R.color.yellow)
                        )
                    })
                    binding.piechart.addPieSlice(user.active?.let { it1 ->
                        PieModel(
                            "Active",
                            it1.toFloat(), ContextCompat.getColor(applicationContext,R.color.blue_pie)
                        )
                    })
                    binding.piechart.addPieSlice(user.recovered?.let { it1 ->
                        PieModel(
                            "Recovered",
                            it1.toFloat(), ContextCompat.getColor(applicationContext,R.color.green_pie)
                        )
                    })
                    binding.piechart.addPieSlice(user.deaths?.let { it1 ->
                        PieModel(
                            "Deaths",
                            it1.toFloat(), ContextCompat.getColor(applicationContext,R.color.red_pie)
                        )
                    })
                   binding.piechart.startAnimation();

                    setUpdatedDate(user.updated)

        //Stop shimmer effect setting data into views
                    stopShimmerEffect()




    }

    private fun setUpdatedDate(date:Long?){
          val prettyTime= PrettyTime()
           val timeMoment=prettyTime.format(date?.let {
               Date(it+1000*60*10)
           })
            binding.tvUpdated.text="Updated "+timeMoment

    }

    private fun openBottomSheetDialog(){
        dialog=BottomSheetDialog(this)
        dialog.setContentView(R.layout.activity_countries)
        val recyclerView=dialog.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager=LinearLayoutManager(this)
        recyclerView?.adapter=CountriesAdapter(this,viewModel.getAllCountriesData(),this)
        dialog.show();
    }

    private fun startShimmerEffect() {
        binding.shimmerActive.startShimmer();
        binding.shimmerConfirm.startShimmer();
        binding.shimmerDeath.startShimmer();
        binding.shimmerRecovered.startShimmer()
        binding.shimmerTest.startShimmer()
    }

    private fun stopShimmerEffect() {
        binding.shimmerActive.stopShimmer();
        binding.shimmerActive.hideShimmer()

        binding.shimmerConfirm.stopShimmer();
        binding.shimmerConfirm.hideShimmer();

        binding.shimmerDeath.stopShimmer();
        binding.shimmerDeath.hideShimmer();

        binding.shimmerRecovered.stopShimmer()
        binding.shimmerRecovered.hideShimmer()

        binding.shimmerTest.stopShimmer()
        binding.shimmerTest.hideShimmer()

    }

    override fun setCountry(user: User) {
        viewModel.setCurrentCountry(user)
        dialog.dismiss()
    }


}

