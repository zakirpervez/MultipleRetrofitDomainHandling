package com.zandroid.multipleretrofitdomainhandling.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RestService {
    @GET("todos")
    suspend fun getTodoListFromJsonPlaceHolder(): Response<ResponseBody>

    @GET("v1/home")
    suspend fun getHelloWorldFromMockDomain(): Response<ResponseBody>
}