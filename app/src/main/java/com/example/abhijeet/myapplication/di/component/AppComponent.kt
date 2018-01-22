package com.example.abhijeet.myapplication.di.component

import android.app.Application

import com.example.abhijeet.myapplication.MyApp
import com.example.abhijeet.myapplication.di.builder.ActivityBuilder
import com.example.abhijeet.myapplication.di.module.AppModule
import com.example.abhijeet.myapplication.di.module.DataModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class,
        ActivityBuilder::class, DataModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: MyApp)

}
