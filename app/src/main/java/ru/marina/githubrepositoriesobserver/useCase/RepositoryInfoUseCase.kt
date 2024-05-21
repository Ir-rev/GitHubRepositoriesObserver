package ru.marina.githubrepositoriesobserver.useCase

import java.lang.IllegalArgumentException
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.maper.RepositoriesInfoModelMapper
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.repository.RepositoriesListRepository
import ru.marina.githubrepositoriesobserver.repository.RepositoryInfoRepository

class RepositoryInfoUseCase @Inject constructor() {
    @Inject
    lateinit var repository: RepositoryInfoRepository

    @Inject
    lateinit var mapper: RepositoriesInfoModelMapper

    suspend fun getInfoRepository(token: String, name: String, owner: String): RepositoriesInfoModel {

        return mapper.invoke(repository.getRepositoryInfo(token, name, owner))

    }



}