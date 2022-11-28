package com.valentin.secondhomework.services.networkService

import com.valentin.secondhomework.model.data.RandomItem
import io.reactivex.Single

interface NetworkService {

    fun getMockData(dataSet: List<RandomItem>): Single<List<RandomItem>>
}