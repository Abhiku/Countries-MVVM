package com.example.abhijeet.myapplication.ui.main

import com.example.abhijeet.myapplication.data.Repository
import com.example.abhijeet.myapplication.ui.base.BaseViewModel
import com.example.abhijeet.myapplication.util.rx.SchedulerProvider
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(repository: Repository, schedulerProvider: SchedulerProvider) :
        BaseViewModel<MainNavigator>(repository, schedulerProvider){

    var searchViewListener: BehaviorSubject<String>? = null

    fun getAllCountries() {
        compositeDisposable.add(mRepository.getAllCountries()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    navigator?.setCountryAdapter(it)
                },
                { t: Throwable ->
                    Timber.e("error is $t")
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun logTextChanged(newText: String?) {
        if (newText != null){

            if (searchViewListener == null) createSearchTextListener()

            searchViewListener?.onNext(newText)
        }
    }

    private fun createSearchTextListener() {

        searchViewListener = BehaviorSubject.create()

        val disposable = searchViewListener!!.debounce(300, TimeUnit.MILLISECONDS)
                .map { t -> t.trim() }
                .distinctUntilChanged()
                .switchMap({ t: String ->  return@switchMap mRepository.getFilteredCountries(t)})
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({

                    Timber.i("item count ${it.size}")

                    navigator?.setCountryAdapter(it)
                },
                { t -> Timber.e("Error is : $t")})

        compositeDisposable.add(disposable)
    }
}
