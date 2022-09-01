package com.zandroid.multipleretrofitdomainhandling.api

class ApiEnvironment<T : Domain>(private var initialValue: T) {
    fun getEnvironment(): Domain {
        return initialValue
    }

    fun setEnvironment(value: T) {
        initialValue = value
    }
}