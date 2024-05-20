package ru.marina.githubrepositoriesobserver.state

import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

sealed class RepositoriesListViewModelState {

    // загрузка списка
    data object Loading : RepositoriesListViewModelState()

    // ошибка списка
    data class Error(val message: String) : RepositoriesListViewModelState()

    // успешная загрузка списка
    class Success(
        var repositoriesModelList: List<RepositoriesModel>
    ) : RepositoriesListViewModelState()
}