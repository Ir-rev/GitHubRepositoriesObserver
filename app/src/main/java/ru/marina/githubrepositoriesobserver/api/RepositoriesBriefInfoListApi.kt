package ru.marina.githubrepositoriesobserver.api

import retrofit2.http.GET
import retrofit2.http.Header
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryBriefInfoEntity

interface RepositoriesBriefInfoListApi {
    @GET("/user/repos")
    suspend fun getRepositoriesBriefInfoList(@Header("Authorization") token: String): List<SingInResponseRepositoryBriefInfoEntity>
}