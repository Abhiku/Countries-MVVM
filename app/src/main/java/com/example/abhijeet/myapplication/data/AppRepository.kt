package com.example.abhijeet.myapplication.data

import com.example.abhijeet.myapplication.data.local.db.AppDbHelper
import com.example.abhijeet.myapplication.data.local.db.DbHelper
import com.example.abhijeet.myapplication.data.local.prefs.PreferencesHelper
import com.example.abhijeet.myapplication.data.model.api.CountryResponse
import com.example.abhijeet.myapplication.data.model.db.Country
import com.example.abhijeet.myapplication.data.remote.Api
import com.example.abhijeet.myapplication.util.Utils
import com.example.abhijeet.myapplication.util.rx.SchedulerProvider
import io.reactivex.Observable
import okhttp3.internal.Util
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository
@Inject
constructor(val api: Api, val dbHelper: DbHelper, val preferencesHelper: PreferencesHelper,
            val schedulerProvider: SchedulerProvider) : Repository{

    override fun getAllCountries(): Observable<List<Country>> {
        return dbHelper.isCountryEmpty()
                .flatMapObservable { t ->
                    if (!t){
                        Timber.d("Dispatching data from database")

                        getAllCountriesDb()
                    }
                    else{
                        Timber.d("Dispatching data from api")

                        getAllCountriesApi()
                    }
                }
    }

    override fun getFilteredCountries(query: String): Observable<List<Country>> {
        return getAllCountries().flatMap { Observable.fromIterable(it) }
                .filter { it.name!!.toLowerCase().startsWith(query.toLowerCase())}
                .toList()
                .toObservable()
    }


    fun getAllCountriesApi() : Observable<List<Country>>{
        return api.getCountries()
                .flatMap { Observable.fromIterable(it) }
                .map {
                    t: CountryResponse ->
                        Timber.i("country is ${t.toString()}")
                        return@map Utils().countryResponseToCountry(t)
                }
                .toList()
                .toObservable()
                .doOnNext {
                    saveCountriesToDb(it)
                }
    }

    fun getAllCountriesDb() : Observable<List<Country>>{
        return dbHelper.getAllCountries()
    }

    fun saveCountriesToDb(countries : List<Country>){
        Observable.fromCallable { dbHelper.saveCountryList(countries) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.io())
                .subscribe {
                    Timber.d("Inserted ${countries.size} users from API in DB...")
                }
    }

}