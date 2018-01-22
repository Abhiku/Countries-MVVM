package com.example.abhijeet.myapplication.ui.main.callback

import com.example.abhijeet.myapplication.data.model.db.Country

interface CountryClickCallback {
    fun onClick(project: Country)
}