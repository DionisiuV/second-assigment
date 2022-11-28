package com.valentin.secondhomework.services.dataService

import com.valentin.secondhomework.model.data.RandomItem
import io.reactivex.Single

interface DataService {

    fun getRandomItemsFromApi(): Single<List<RandomItem>>

    fun getRandomItemsFromCache(): List<RandomItem>

    fun setRandomItemsToCache(dataSet: List<RandomItem>)
}