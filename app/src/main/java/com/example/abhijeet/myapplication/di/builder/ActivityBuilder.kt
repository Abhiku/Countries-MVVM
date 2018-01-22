package com.example.abhijeet.myapplication.di.builder

import com.example.abhijeet.myapplication.ui.main.MainActivity
import com.example.abhijeet.myapplication.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun bindMainActivity() : MainActivity
}
