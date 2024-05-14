package ru.marina.githubrepositoriesobserver.provider

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.api.RepositoryInfoApi

class RepositoryInfoProviderRetrofit @Inject constructor() {
    @Inject
    lateinit var providerRetrofit: ProviderRetrofit

    fun getUserLoginProviderRetrofit(): RepositoryInfoApi {
        return providerRetrofit.providerRetrofit().create(RepositoryInfoApi::class.java)
    }
}