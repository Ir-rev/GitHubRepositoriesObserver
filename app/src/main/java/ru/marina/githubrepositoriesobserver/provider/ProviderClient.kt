package ru.marina.githubrepositoriesobserver.provider

import javax.inject.Inject
import okhttp3.OkHttpClient

class ProviderClient @Inject constructor(private val interceptor: Interceptor) {


    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor.createAuthorizationInterceptor())
            .addInterceptor(interceptor.createLoggingInterceptor())
            .build()
    }
}