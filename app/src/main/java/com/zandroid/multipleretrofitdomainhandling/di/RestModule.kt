package com.zandroid.multipleretrofitdomainhandling.di

import android.util.Log
import com.zandroid.multipleretrofitdomainhandling.api.DomainHelper
import com.zandroid.multipleretrofitdomainhandling.api.RestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RestModule {
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG,
            message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(httpLoggingInterceptor)
        }
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, helper: DomainHelper): Retrofit = Retrofit.Builder()
        .apply {
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(helper.selectedDomain.url)
        }
        .build()

    @Provides
    fun provideRestService(retrofit: Retrofit): RestService = retrofit.create(RestService::class.java)

    companion object {
        private const val TAG = "RestApi"
    }
}