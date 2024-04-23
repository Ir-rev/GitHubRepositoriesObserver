package ru.marina.githubrepositoriesobserver.provider

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.api.RepositoriesBriefInfoListApi
import ru.marina.githubrepositoriesobserver.api.UserLoginApi

class RepositoriesBriefListProviderRetrofit @Inject constructor() {
    @Inject
    lateinit var providerRetrofit: ProviderRetrofit

    fun getUserLoginProviderRetrofit(): RepositoriesBriefInfoListApi {
        return providerRetrofit.providerRetrofit().create(RepositoriesBriefInfoListApi::class.java)
    }
}