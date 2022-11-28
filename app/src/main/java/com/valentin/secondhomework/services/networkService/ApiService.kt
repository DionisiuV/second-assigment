package com.valentin.secondhomework.services.networkService

import com.valentin.secondhomework.model.data.RandomItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("mock-data")
    fun getMockData(): Single<List<RandomItem>>
}