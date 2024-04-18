package ru.marina.githubrepositoriesobserver.retrofit

import javax.inject.Inject
import retrofit2.http.GET
import ru.marina.githubrepositoriesobserver.model.RepositoriesList


interface RepositoryApi {
    // в гет засунуть конечную точку запроса
    @GET("repository.json")
    fun getAllInfoRepositoryList():RepositoriesList
}