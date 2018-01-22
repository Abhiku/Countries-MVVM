package com.example.abhijeet.myapplication.data.local.db

import com.example.abhijeet.myapplication.data.model.db.Country
import io.reactivex.Observable
import io.reactivex.Single

interface DbHelper{

    fun isCountryEmpty() : Single<Boolean>

    fun getAllCountries() : Observable<List<Country>>

    fun insertCountry(country: Country)

    fun saveCountryList(countries : List<Country>)

}
