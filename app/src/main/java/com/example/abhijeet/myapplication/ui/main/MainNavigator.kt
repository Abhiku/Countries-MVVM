package com.example.abhijeet.myapplication.ui.main

import com.example.abhijeet.myapplication.data.model.db.Country

interface MainNavigator {
    fun setCountryAdapter(list: List<Country>)
}
