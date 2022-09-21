package com.zandroid.multipleretrofitdomainhandling.source

import com.zandroid.multipleretrofitdomainhandling.api.RestService
import com.zandroid.multipleretrofitdomainhandling.api.SafeApi
import javax.inject.Inject
import javax.inject.Singleton

class ApiDataSource @Inject constructor(private val restService: RestService): SafeApi() {
    @Throws(Exception::class)
    suspend fun getTodoListFromJsonPlaceHolder() = execute {
        restService.getTodoListFromJsonPlaceHolder()
    }

    @Throws(Exception::class)
    suspend fun getHelloWorldFromMockDomain() = execute {
        restService.getHelloWorldFromMockDomain()
    }
}