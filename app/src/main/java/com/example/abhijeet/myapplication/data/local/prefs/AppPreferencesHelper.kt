package com.example.abhijeet.myapplication.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

import com.example.abhijeet.myapplication.di.qualifiers.PreferenceInfo

import javax.inject.Inject

class AppPreferencesHelper
@Inject
constructor(context: Context, @PreferenceInfo prefFileName: String) : PreferencesHelper {

    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }
}
