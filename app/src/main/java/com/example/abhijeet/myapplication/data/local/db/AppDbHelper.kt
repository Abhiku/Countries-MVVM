package com.example.abhijeet.myapplication.data.local.db

import com.example.abhijeet.myapplication.data.model.db.Country
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper
@Inject
constructor(private val mAppDatabase: AppDataBase) : DbHelper{
    override fun isCountryEmpty(): Single<Boolean> {
        return mAppDatabase.countryDao().getCountryCount()
                .map{ t: Int -> t == 0 }
    }

    override fun getAllCountries(): Observable<List<Country>> {
        return mAppDatabase.countryDao().getAllCountries()
                .filter{ it.isNotEmpty() }
                .toObservable()
    }

    override fun insertCountry(country: Country) {
        mAppDatabase.countryDao().insert(country)
    }

    override fun saveCountryList(countries: List<Country>){
        mAppDatabase.countryDao().insertAll(countries)
    }

}
