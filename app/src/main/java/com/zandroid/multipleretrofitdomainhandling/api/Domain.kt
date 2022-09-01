package com.zandroid.multipleretrofitdomainhandling.api

import com.zandroid.multipleretrofitdomainhandling.BuildConfig

sealed class Domain {
    abstract val domainName: String
    abstract val domainType: DomainType
    abstract val url: String
}
data class JsonPlaceHolderDomain(val tag: String = "json_place_holder"): Domain() {
    override val domainName: String
        get() = tag
    override val domainType: DomainType
        get() = DomainType.JSON_PLACE_HOLDER
    override val url: String
        get() = BuildConfig.JSON_PLACE_HOLDER_BASE_URL
}
data class MockDomain(val tag: String = "mock"): Domain() {
    override val domainName: String
        get() = tag
    override val domainType: DomainType
        get() = DomainType.MOCK
    override val url: String
        get() = BuildConfig.MOCK_BASE_URL
}

