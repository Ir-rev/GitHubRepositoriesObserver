package ru.marina.githubrepositoriesobserver.state

import dagger.hilt.android.AndroidEntryPoint
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

sealed class RepositoriesListViewModelState {

    object Idle: RepositoriesListViewModelState()

    // загрузка списка
    object Loading : RepositoriesListViewModelState()

    // ошибка списка
    data class Error(val message: String) : RepositoriesListViewModelState()

    // успешная загрузка списка
    class Success(
        var repositoriesModelList: List<RepositoriesModel>
    ) : RepositoriesListViewModelState()
}