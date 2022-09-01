package com.zandroid.multipleretrofitdomainhandling.api

import retrofit2.Response

open class SafeApi {
    @Throws(Exception::class)
    suspend fun <T> execute(call: suspend () -> Response<T>): T {
        val apiResponse = call.invoke()
        return if (apiResponse.isSuccessful) {
            apiResponse.body() ?: throw Exception("Response body is null ${apiResponse.code()}")
        } else {
            var error = apiResponse.message()
            if (error.isEmpty()) {
                error = "Something went wrong"
            }
            throw Exception(
                "$error ${apiResponse.code()}"
            )
        }
    }
}