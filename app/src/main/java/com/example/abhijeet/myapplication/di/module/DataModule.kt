package com.example.abhijeet.myapplication.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.example.abhijeet.myapplication.data.AppRepository
import com.example.abhijeet.myapplication.data.Repository
import com.example.abhijeet.myapplication.data.local.db.AppDataBase
import com.example.abhijeet.myapplication.data.local.db.AppDbHelper
import com.example.abhijeet.myapplication.data.local.db.DbHelper
import com.example.abhijeet.myapplication.data.local.prefs.AppPreferencesHelper
import com.example.abhijeet.myapplication.data.local.prefs.PreferencesHelper
import com.example.abhijeet.myapplication.di.qualifiers.DatabaseInfo
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by abhijeet on 18/1/18.
 */
@Module
abstract class DataModule{

    @Binds
    internal abstract fun bindDbHelper(appDbHelper: AppDbHelper): DbHelper

    @Binds
    internal abstract fun bindAppPreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper

    @Binds
    internal abstract fun bindAppRepository(appRepository: AppRepository): Repository

}