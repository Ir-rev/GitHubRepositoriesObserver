package ru.marina.githubrepositoriesobserver.useCase

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.maper.RepositoriesModelMapper
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel
import ru.marina.githubrepositoriesobserver.repository.RepositoriesListRepository

class RepositoryListUseCase @Inject constructor() {

    @Inject
    lateinit var repository: RepositoriesListRepository

    @Inject
    lateinit var mapper: RepositoriesModelMapper
    suspend fun getRepositoriesList(token: String): List<RepositoriesModel> {
        val listModel = repository.getRepositoriesList(token)
        val listMapper = mutableListOf<RepositoriesModel>()
        for (element in listModel) {
            listMapper.add(mapper.invoke(element))
        }

        return listMapper

    }
}
//    suspend fun getOwner(token: String): String?{
//        return getRepositoriesList(token).first().owner
//    }
//}