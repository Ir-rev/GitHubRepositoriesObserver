package ru.marina.githubrepositoriesobserver.retrofit

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.GET
import ru.marina.githubrepositoriesobserver.model.RepositoriesListApi

@AndroidEntryPoint
interface RepositoryApi {
    // в гет засунуть конечную точку запроса
    @GET("/user")
    fun getAllInfoRepositoryList(token: String): RepositoriesListApi
}