package com.example.abhijeet.myapplication.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean

import com.example.abhijeet.myapplication.data.Repository
import com.example.abhijeet.myapplication.util.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N>(protected val mRepository: Repository,
                                protected val mSchedulerProvider: SchedulerProvider) : ViewModel() {

    var navigator: N? = null
    val isLoading = ObservableBoolean(false)

    val compositeDisposable: CompositeDisposable

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
