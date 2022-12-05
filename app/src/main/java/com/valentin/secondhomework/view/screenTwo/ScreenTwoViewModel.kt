package com.valentin.secondhomework.view.screenTwo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valentin.secondhomework.model.data.ApiResponse
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.model.service.dataService.DataService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ScreenTwoViewModel(
    private val dataService: DataService,
) : ViewModel() {

    private val randomItemsLiveData = MutableLiveData<ApiResponse<List<RandomItem>>>()
    private val disposables = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getRandomItems(): LiveData<ApiResponse<List<RandomItem>>> {
        if (randomItemsIsNotCached()) {
            fetchRandomItemsAndSetToCacheAndLiveData()
        } else {
            setRandomItemsLiveDataFromCache()
        }

        return randomItemsLiveData
    }

    fun refreshData(): LiveData<ApiResponse<List<RandomItem>>> {
        randomItemsLiveData.value = ApiResponse.Loading
        fetchRandomItemsAndSetToCacheAndLiveData()

        return randomItemsLiveData
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
            .doOnSubscribe {
                randomItemsLiveData.value = ApiResponse.Loading
            }
            .subscribe(
                { list ->
                    dataService.setRandomItemsToCache(list)
                    randomItemsLiveData.value = ApiResponse.Success(list)
                },
                { error ->
                    randomItemsLiveData.value = ApiResponse.Error(error.message.toString())
                }
            )
    }

    private fun setRandomItemsLiveDataFromCache() {
        Log.d("DEBUG_TAG", "Fetching items from cache")

        randomItemsLiveData.value = ApiResponse.Success(dataService.getRandomItemsFromCache())
    }

    private fun randomItemsIsNotCached(): Boolean {
        return dataService.getRandomItemsFromCache().isEmpty()
    }
}