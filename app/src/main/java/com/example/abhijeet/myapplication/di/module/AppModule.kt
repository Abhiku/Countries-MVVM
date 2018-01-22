package com.example.abhijeet.myapplication.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.abhijeet.myapplication.BuildConfig
import com.example.abhijeet.myapplication.data.local.db.AppDataBase
import com.example.abhijeet.myapplication.data.remote.Api

import com.example.abhijeet.myapplication.di.qualifiers.DatabaseInfo
import com.example.abhijeet.myapplication.di.qualifiers.PreferenceInfo
import com.example.abhijeet.myapplication.util.AppConstants
import com.example.abhijeet.myapplication.util.rx.AppSchedulerProvider
import com.example.abhijeet.myapplication.util.rx.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    val baseUrl = "https://restcountries.eu/"

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideSchedulers() : SchedulerProvider{
        return AppSchedulerProvider()
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun provideDatabase(@DatabaseInfo dbName: String, context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, dbName).fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideMyApi(gson: Gson, okHttpClient: OkHttpClient): Api {
        val httpClientBuilder = okHttpClient.newBuilder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(Api::class.java)
    }
}
