package com.example.abhijeet.myapplication.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.abhijeet.myapplication.data.model.db.Country
import io.reactivex.Observable
import android.arch.persistence.room.OnConflictStrategy
import io.reactivex.Single


@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAllCountries() : Single<List<Country>>

    @Query("SELECT COUNT(*) FROM country")
    fun getCountryCount() : Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<Country>)
}