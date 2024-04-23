package ru.marina.githubrepositoriesobserver.retrofit

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.GET
import retrofit2.http.Header
import ru.marina.githubrepositoriesobserver.model.RepositoriesListApi
import ru.marina.githubrepositoriesobserver.repository.SignInResponseEntity

interface RepositoryApi {

    @GET("/user")
    suspend fun getAllInfoUserList(@Header("Authorization") token: String): SignInResponseEntity
}