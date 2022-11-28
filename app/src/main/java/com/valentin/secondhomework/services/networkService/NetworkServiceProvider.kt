package com.valentin.secondhomework.services.networkService

import com.valentin.secondhomework.model.data.RandomItem
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider : NetworkService {

    private fun getMockClient(dataSet: List<RandomItem>): ApiService {
        return getRetrofit(getOkHttpClient(MockInterceptor(dataSet))).create(ApiService::class.java)
    }

    private fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://mock-api.lol")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    override fun getMockData(dataSet: List<RandomItem>): Single<List<RandomItem>> {
        return getMockClient(dataSet).getMockData()
    }
}