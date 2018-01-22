package com.example.abhijeet.myapplication.util

import android.content.Context
import com.example.abhijeet.myapplication.data.model.api.CountryResponse
import com.example.abhijeet.myapplication.data.model.db.Country
import timber.log.Timber
import android.util.DisplayMetrics



/**
 * Created by abhijeet on 18/1/18.
 */
class Utils{

    fun countryResponseToCountry(countryResponse: CountryResponse) : Country{

        var country = Country(countryResponse.alpha2Code)

        country.name = countryResponse.name
        country.topLevelDomain = countryResponse.topLevelDomain
        country.alpha3Code = countryResponse.alpha3Code
        country.callingCodes = countryResponse.callingCodes
        country.capital = countryResponse.capital
        country.altSpellings = countryResponse.altSpellings
        country.region = countryResponse.region
        country.subregion = countryResponse.subregion
        country.population = countryResponse.population
        try {
            country.lat = countryResponse.latlng?.get(0)
            country.lng = countryResponse.latlng?.get(1)
        }
        catch (t : IndexOutOfBoundsException){
            Timber.e("No latlng : ${countryResponse.name}")
        }
        country.demonym = countryResponse.demonym
        country.area = countryResponse.area
        country.gini = countryResponse.gini
        country.timezones = countryResponse.timezones
        country.borders = countryResponse.borders
        country.gini = countryResponse.gini
        country.nativeName = countryResponse.nativeName
        country.numericCode = countryResponse.numericCode
        try {
            country.currencies = countryResponse.currencies?.map { currency -> currency.name!! }
        }
        catch (t : KotlinNullPointerException){
            Timber.e("No currencies : ${countryResponse.name}")
        }

        try {
            country.languages = countryResponse.languages?.map { language -> language.name!! }
        }
        catch (t : KotlinNullPointerException){
            Timber.e("No languages : ${countryResponse.name}")
        }

        country.flag = countryResponse.flag

        return country
    }

}