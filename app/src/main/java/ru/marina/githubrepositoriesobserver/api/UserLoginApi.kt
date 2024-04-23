package ru.marina.githubrepositoriesobserver.api

import retrofit2.http.GET
import retrofit2.http.Header
import ru.marina.githubrepositoriesobserver.entity.SignInResponseUserLoginEntity

interface UserLoginApi {

    @GET("/user")
    suspend fun getUserLogin(@Header("Authorization") token: String): SignInResponseUserLoginEntity
}