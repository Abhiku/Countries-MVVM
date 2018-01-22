package com.example.abhijeet.myapplication.ui.main

import com.example.abhijeet.myapplication.data.Repository
import com.example.abhijeet.myapplication.util.rx.SchedulerProvider

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(repository: Repository,
                                        schedulerProvider: SchedulerProvider): MainViewModel {
        return MainViewModel(repository, schedulerProvider)
    }

}
