package ru.marina.githubrepositoriesobserver.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import ru.marina.githubrepositoriesobserver.entity.SignInResponseUserLoginEntity

interface UserLoginApi {

    @GET("/user")
    @Headers("Accept: application/vnd.github+json; X-GitHub-Api-Version: 2022-11-28")
    suspend fun getUserLogin(@Header("Authorization") token: String): SignInResponseUserLoginEntity
}