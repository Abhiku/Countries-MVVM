package com.example.abhijeet.myapplication.util

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class Converters {
    @TypeConverter
    public fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    public fun fromStringList(list: List<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}