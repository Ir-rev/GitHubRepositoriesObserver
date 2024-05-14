package ru.marina.githubrepositoriesobserver.repository

import javax.inject.Inject
import javax.inject.Singleton
import ru.marina.githubrepositoriesobserver.Const
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryInfo
import ru.marina.githubrepositoriesobserver.provider.RepositoryInfoProviderRetrofit

@Singleton
class RepositoryInfoRepository @Inject constructor() {
    @Inject
    lateinit var retrofit: RepositoryInfoProviderRetrofit

    suspend fun getRepositoryInfo(token: String): SingInResponseRepositoryInfo{
        return retrofit.getUserLoginProviderRetrofit()
            .getRepositoryInfo("${Const.START_POINT} $token")

    }
}
