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

    suspend fun getRepositoryInfo(token: String, owner: String, repo: String): SingInResponseRepositoryInfo{
        return retrofit.getUserLoginProviderRetrofit()
            .getRepositoryInfo(
                token = "${Const.START_POINT} $token",
                owner = "${Const.START_OWNER} $owner",
                repo = "${Const.START_REPO} $repo"
            )

    }
//    suspend fun getRepositoryInfo(token: String, owner: String, repo: String): SingInResponseRepositoryInfo{
//        return retrofit.getUserLoginProviderRetrofit()
//            .getRepositoryInfo(
//                token = "${Const.START_POINT} $token",
//                owner = "${Const.START_OWNER} $owner",
//                repo = "${Const.START_REPO} $repo"
//            )
//
//    }
}
