package ru.marina.githubrepositoriesobserver.provider

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.api.UserLoginApi

class UserLoginProviderRetrofit @Inject constructor() {
    @Inject
    lateinit var providerRetrofit: ProviderRetrofit

    fun getUserLoginProviderRetrofit(): UserLoginApi{
       return providerRetrofit.providerRetrofit().create(UserLoginApi::class.java)
    }
}