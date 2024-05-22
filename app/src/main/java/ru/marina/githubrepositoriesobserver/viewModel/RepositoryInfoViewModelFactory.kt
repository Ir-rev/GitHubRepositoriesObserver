package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.useCase.RepositoryInfoUseCase


class RepositoryInfoViewModelFactory(
    private val name: String,
    private val owner: String,
    private val useCase: RepositoryInfoUseCase,
    private val databaseSaveToken: DatabaseSaveToken,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryInfoViewModel(owner, name, useCase, databaseSaveToken) as T
    }
}