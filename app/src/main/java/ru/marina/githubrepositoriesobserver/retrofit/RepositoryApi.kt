package ru.marina.githubrepositoriesobserver.retrofit

import retrofit2.http.GET
import ru.marina.githubrepositoriesobserver.model.RepositoriesList


interface RepositoryApi {
    @GET("repository.json")
    fun getAllInfoRepositoryList():RepositoriesList
}