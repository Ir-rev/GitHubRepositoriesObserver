package ru.marina.githubrepositoriesobserver.state

import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

sealed class RepositoryInfoViewModelState {

    object Loading : RepositoryInfoViewModelState()

    data class Error(val message: String) : RepositoryInfoViewModelState()

    class Success(
        var repositoriesModelList: RepositoriesInfoModel
    ) : RepositoryInfoViewModelState()
}