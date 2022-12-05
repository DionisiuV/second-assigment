package com.valentin.secondhomework.model.service.networkService

import com.google.gson.Gson
import com.valentin.secondhomework.model.data.RandomItem
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(
    private val dataSet: List<RandomItem>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val jsonDataSet = Gson().toJson(dataSet)

        return Response.Builder()
            .request(chain.request())
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(getResponseFromMockApi)
            .body(jsonDataSet.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

const val getResponseFromMockApi = """
        {"message": "Mock api reached"}
        """