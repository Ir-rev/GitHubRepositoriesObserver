package ru.marina.githubrepositoriesobserver.repository

import javax.inject.Inject
import javax.inject.Singleton
import ru.marina.githubrepositoriesobserver.Const
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryBriefInfoEntity
import ru.marina.githubrepositoriesobserver.provider.RepositoriesBriefListProviderRetrofit
@Singleton
class RepositoriesListRepository @Inject constructor() {
    @Inject
    lateinit var retrofit: RepositoriesBriefListProviderRetrofit

    suspend fun getRepositoriesList(token: String): List<SingInResponseRepositoryBriefInfoEntity>{
        return retrofit.getUserLoginProviderRetrofit()
            .getRepositoriesBriefInfoList("${Const.START_POINT} $token")

    }
}