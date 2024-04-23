package ru.marina.githubrepositoriesobserver.useCase

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.maper.RepositoriesModelMapper
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel
import ru.marina.githubrepositoriesobserver.repository.RepositoriesListRepository

class RepositoryListUseCase @Inject constructor() {
    @Inject
    //private val repositoryListUseCase: RepositoryListUseCase?= null
    lateinit var repository: RepositoriesListRepository
    private var mapper: RepositoriesModelMapper?= null

        suspend fun getRepositoriesList(token: String): List<RepositoriesModel> {
            return mapper?.invoke(repository.getRepositoriesList(token).map { list -> list.name})
//        return repository.getRepositoriesList(token)
            //.map { list -> list.reposUrl }
    }
}