package com.valentin.secondhomework.model.service.dataService

import android.util.Log
import com.valentin.secondhomework.model.data.RandomItem
import com.valentin.secondhomework.model.service.networkService.NetworkService
import io.reactivex.Single

class DataServiceProvider(
    private val networkService: NetworkService
) : DataService {

    private var randomItemList: List<RandomItem> = listOf()


    override fun getRandomItemsFromApi(): Single<List<RandomItem>> {
        return networkService.getMockData(getHardCodedValue())
    }

    override fun getRandomItemsFromCache(): List<RandomItem> {
        return randomItemList
    }

    override fun setRandomItemsToCache(dataSet: List<RandomItem>) {
        Log.d("DEBUG_TAG", "DataService.setRandomItemsToCache()  -> $dataSet")
        this.randomItemList = dataSet
    }

    private fun getHardCodedValue(): List<RandomItem> {
        return listOf(
            RandomItem("Item1", "Red", "Desc1"),
            RandomItem("Item2", "Green", "Desc2"),
            RandomItem("Item3", "Blue", "Desc3"),
            RandomItem("Item4", "Red", "Desc4"),
            RandomItem("Item5", "Green", "Desc5"),
            RandomItem("Item6", "Blue", "Desc6")
        )
    }
}