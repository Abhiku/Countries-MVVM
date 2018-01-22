package com.example.abhijeet.myapplication

import android.app.Activity
import android.app.Application
import com.example.abhijeet.myapplication.di.component.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject


class MyApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        instance = this

        RxJavaPlugins.setErrorHandler({ Timber.e(it) })

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }

    companion object {

        lateinit var instance: MyApp
            private set
    }
}