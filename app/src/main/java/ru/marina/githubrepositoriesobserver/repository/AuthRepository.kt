package ru.marina.githubrepositoriesobserver.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marina.githubrepositoriesobserver.Const
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel
import ru.marina.githubrepositoriesobserver.retrofit.RepositoryApi

@Singleton
class AuthRepository @Inject constructor(
    // todo add RepositoryApi через inject
) {

//    suspend fun getFullRepositoriesList(token: String): List<AuthResponseModel> {
//        return providerRetrofit().getAllInfoUserList().repositoriesList
//    }

    suspend fun authUser(token: String): String {
        return providerRetrofit()
            .getAllInfoUserList("${Const.START_POINT} $token")
            .login
    }

    // убрать в отдельный класс
    private val baseUrl = "https://api.github.com"

    private fun providerRetrofit(): RepositoryApi {
        val contentType = Const.ACCEPT.toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(provideClient())
            .build()
            .create(RepositoryApi::class.java)
    }

    private fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createAuthorizationInterceptor())
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
                .also { it.header(Const.ACCEPT_HEADER, Const.ACCEPT) }
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}