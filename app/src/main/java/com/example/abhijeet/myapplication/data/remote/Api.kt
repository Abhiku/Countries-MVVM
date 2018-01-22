package com.example.abhijeet.myapplication.data.remote

import com.example.abhijeet.myapplication.data.model.api.CountryResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("rest/v2/all")
    fun getCountries(): Observable<List<CountryResponse>>
}