package com.example.abhijeet.myapplication.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "country")
data class Country (@PrimaryKey
                    @ColumnInfo(name = "alpha2Code")
                    var alpha2Code: String){
    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "topLevelDomain")
    var topLevelDomain: List<String>? = null
    @ColumnInfo(name = "alpha3Code")
    var alpha3Code: String? = null
    @ColumnInfo(name = "callingCodes")
    var callingCodes: List<String>? = null
    @ColumnInfo(name = "capital")
    var capital: String? = null
    @ColumnInfo(name = "altSpellings")
    var altSpellings: List<String>? = null
    @ColumnInfo(name = "region")
    var region: String? = null
    @ColumnInfo(name = "subregion")
    var subregion: String? = null
    @ColumnInfo(name = "population")
    var population: Int? = null
    @ColumnInfo(name = "lat")
    var lat: Double? = null
    @ColumnInfo(name = "lng")
    var lng: Double?= null
    @ColumnInfo(name = "demonym")
    var demonym: String? = null
    @ColumnInfo(name = "area")
    var area: Double? = null
    @ColumnInfo(name = "gini")
    var gini: Double? = null
    @ColumnInfo(name = "timezones")
    var timezones: List<String>? = null
    @ColumnInfo(name = "borders")
    var borders: List<String>? = null
    @ColumnInfo(name = "nativeName")
    var nativeName: String? = null
    @ColumnInfo(name = "numericCode")
    var numericCode: String? = null
    @ColumnInfo(name = "currencies")
    var currencies: List<String>? = null
    @ColumnInfo(name = "languages")
    var languages: List<String>? = null
    @ColumnInfo(name = "flag")
    var flag: String? = null
}