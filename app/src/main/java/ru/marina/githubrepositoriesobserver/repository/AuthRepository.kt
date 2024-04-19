package ru.marina.githubrepositoriesobserver.repository

import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel
import ru.marina.githubrepositoriesobserver.retrofit.RepositoryApi

@AndroidEntryPoint
@Singleton
class AuthRepository @Inject constructor() {
    private var repositoriesListParseJson = listOf<AuthResponseModel>()
    private val baseUrl = "https://github.com/"
    //+ getLoginUser() + "?"


    private suspend fun getFullRepositoriesList(): List<AuthResponseModel> {
        if (repositoriesListParseJson.isEmpty()) {
            repositoriesListParseJson = providerRetrofit().getAllInfoRepositoryList().repositoriesList
        }
        return repositoriesListParseJson
    }

    private suspend fun authUser(token: String): String {
        return providerRetrofit()
            .getAllInfoRepositoryList(token)
            .repositoriesList
            .map { list -> list.login }
            .first()
            ?: ""

    }

    @Provides
    @Singleton
    fun providerRetrofit(): RepositoryApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baseUrl)
            .build()
            .create(RepositoryApi::class.java)


}