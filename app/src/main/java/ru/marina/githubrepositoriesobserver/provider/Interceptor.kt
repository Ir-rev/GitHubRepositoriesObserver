package ru.marina.githubrepositoriesobserver.provider

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import ru.marina.githubrepositoriesobserver.Const

class Interceptor @Inject constructor() {
    fun createAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
                .also { it.header(Const.ACCEPT_HEADER, Const.ACCEPT) }
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }

    fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}