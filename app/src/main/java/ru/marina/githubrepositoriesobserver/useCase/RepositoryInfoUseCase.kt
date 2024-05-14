package ru.marina.githubrepositoriesobserver.useCase

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.maper.RepositoriesInfoModelMapper
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.repository.RepositoryInfoRepository

class RepositoryInfoUseCase @Inject constructor() {
    @Inject
    lateinit var repository: RepositoryInfoRepository
    @Inject
    lateinit var mapper: RepositoriesInfoModelMapper

    suspend fun getInfoRepository(token: String): RepositoriesInfoModel{
        return  mapper.invoke(repository.getRepositoryInfo(token))


    }
}