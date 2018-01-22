package com.example.abhijeet.myapplication.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.abhijeet.myapplication.R
import android.databinding.DataBindingUtil
import com.example.abhijeet.myapplication.data.model.db.Country
import com.example.abhijeet.myapplication.databinding.AdapterCountryBinding
import com.example.abhijeet.myapplication.ui.main.callback.CountryClickCallback


/**
 * Created by abhijeet on 18/1/18.
 */
class CountryAdapter(countryClickCallback: CountryClickCallback?) : RecyclerView.Adapter<CountryViewHolder>() {

    var countryList : List<Country> = emptyList()
    var mCountryClickCallback : CountryClickCallback? = null

    init {
        mCountryClickCallback = countryClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CountryViewHolder {

        val binding = AdapterCountryBinding.inflate(LayoutInflater.from(parent!!.context),
                parent,false)

        binding.callback = mCountryClickCallback

        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder?, position: Int) {
        holder?.binding?.country= countryList.get(position)
        holder?.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}