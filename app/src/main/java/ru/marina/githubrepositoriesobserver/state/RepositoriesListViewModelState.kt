package ru.marina.githubrepositoriesobserver.state

import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

sealed class RepositoriesListViewModelState {
    object Loading: RepositoriesListViewModelState()
    data class Error(val message: String): RepositoriesListViewModelState()
    class Success(
       var repositoriesModelList: List<RepositoriesModel>
    ): RepositoriesListViewModelState()
}