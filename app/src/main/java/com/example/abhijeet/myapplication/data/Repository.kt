package com.example.abhijeet.myapplication.data

import com.example.abhijeet.myapplication.data.model.db.Country
import io.reactivex.Observable

/**
 * Created by abhijeet on 18/1/18.
 */
interface Repository{
    fun getAllCountries(): Observable<List<Country>>

    fun getFilteredCountries(t: String): Observable<List<Country>>

}