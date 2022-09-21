package com.zandroid.multipleretrofitdomainhandling.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainHelper @Inject constructor(){
    var selectedDomain: Domain = JsonPlaceHolderDomain()
    fun changeBaseUrl(domainType: DomainType) {
        selectedDomain = if (domainType == DomainType.MOCK) {
           MockDomain()
        } else {
            JsonPlaceHolderDomain()
        }
    }
}