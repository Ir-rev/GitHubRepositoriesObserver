package ru.marina.githubrepositoriesobserver.state

import dagger.hilt.android.AndroidEntryPoint
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

sealed class RepositoriesListViewModelState {

    // загрузка данных
    object Loading : RepositoriesListViewModelState()

    // ошибка
    data class Error(val message: String) : RepositoriesListViewModelState()

    // успешная загрузка
    class Success(
        var repositoriesModelList: List<RepositoriesModel>
    ) : RepositoriesListViewModelState()
}