package com.example.abhijeet.myapplication.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.abhijeet.myapplication.data.local.db.dao.CountryDao
import com.example.abhijeet.myapplication.data.model.db.Country
import com.example.abhijeet.myapplication.util.Converters

@Database(entities = arrayOf(Country::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}