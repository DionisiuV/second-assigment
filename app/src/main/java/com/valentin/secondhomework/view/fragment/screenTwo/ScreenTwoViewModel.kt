package com.valentin.secondhomework.view.fragment.screenTwo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valentin.secondhomework.R
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.services.dataService.DataService
import com.valentin.secondhomework.services.navService.MainActivityNavService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ScreenTwoViewModel(
    private val dataService: DataService,
    private val navService: MainActivityNavService
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private var observer: (String, Any?) -> Unit = { _, _ -> }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun setObserver(observer: (String, Any?) -> Unit) {
        Log.d("DEBUG_TAG", observer.toString())
        this.observer = observer
    }

    fun handleUiEvents(eventName: String) {
        when (eventName) {
            "loadDataFromDataSources" -> loadDataFromDataSources()
            "refreshData" -> refreshData()
            else -> throw Exception("Unhandled ViewModel event")
        }
    }

    private fun refreshData() {
        fetchRandomItemsAndSetToCacheAndLiveData()
    }

    private fun loadDataFromDataSources() {
        if (randomItemsIsNotCached())
            fetchRandomItemsAndSetToCacheAndLiveData()
        else
            fetchRandomItemsFromCache()
    }

    private fun fetchRandomItemsAndSetToCacheAndLiveData() {
        Log.d("DEBUG_TAG", "Fetching items from api")
        disposables.add(getRandomItemsFromApi())
    }

    private fun getRandomItemsFromApi(): Disposable {
        return dataService.getRandomItemsFromApi()
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { postIsLoadingState() }
            .subscribe(
                { handleApiResponseSuccess(it) },
                { handleApiResponseError(it.message.toString()) }
            )
    }

    private fun handleApiResponseSuccess(itemList: List<RandomItem>) {
        observer("gotElementsFromApi", itemList)
        dataService.setRandomItemsToCache(itemList)
    }

    private fun handleApiResponseError(errorMessage: String) {
        observer("gotElementsError", errorMessage)
    }

    private fun postIsLoadingState() {
        observer("gotElementsLoading", null)
    }

    private fun fetchRandomItemsFromCache() {
        Log.d("DEBUG_TAG", "Fetching items from cache")

        observer("gotElementsFromCache", dataService.getRandomItemsFromCache())
    }

    private fun randomItemsIsNotCached(): Boolean {
        return dataService.getRandomItemsFromCache().isEmpty()
    }

    fun goToFirstFragment() {
        navService.navigateTo(R.id.action_screenTwoFragment_to_screenOneFragment)
    }
}