package com.zandroid.multipleretrofitdomainhandling.repo

import com.zandroid.multipleretrofitdomainhandling.source.ApiDataSource
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(private val source: ApiDataSource) {
    @Throws(Exception::class)
    suspend fun getTodoListFromJsonPlaceHolder() = source.getTodoListFromJsonPlaceHolder()
    @Throws(Exception::class)
    suspend fun getHelloWorldFromMockDomain() = source.getHelloWorldFromMockDomain()
}