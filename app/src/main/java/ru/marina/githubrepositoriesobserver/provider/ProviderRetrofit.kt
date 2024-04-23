package ru.marina.githubrepositoriesobserver.provider

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.marina.githubrepositoriesobserver.Const

class ProviderRetrofit @Inject constructor(private val providerClient: ProviderClient) {
    private val baseUrl = "https://api.github.com"

    fun providerRetrofit(): Retrofit {
        val contentType = Const.ACCEPT.toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(providerClient.provideClient())
            .build()

    }
}