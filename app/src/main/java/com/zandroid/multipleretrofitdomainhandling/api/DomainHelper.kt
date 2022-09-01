package com.zandroid.multipleretrofitdomainhandling.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainHelper @Inject constructor(){
    private val environment = ApiEnvironment<Domain>(JsonPlaceHolderDomain())
    val baseUrl = environment.getEnvironment().url
    fun changeBaseUrl(domainType: DomainType) {
        if (domainType == DomainType.MOCK) {
            environment.setEnvironment(MockDomain())
        } else {
            environment.setEnvironment(JsonPlaceHolderDomain())
        }
    }
}