package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel
import ru.marina.githubrepositoriesobserver.state.AuthUserTokenViewModelState
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryListUseCase

@HiltViewModel
class RepositoriesListViewModel : ViewModel() {
    @Inject
    lateinit var repositoryListUseCase: RepositoryListUseCase

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken
    private val token = databaseSaveToken.getToken()
    private lateinit var _listRepositories: List<RepositoriesModel>
    val listRepositories:List<RepositoriesModel> =_listRepositories

    // нужно ли сохранять состояние в флоу?
    private val _viewStateFlow: MutableStateFlow<RepositoriesListViewModelState> =
        MutableStateFlow(RepositoriesListViewModelState.Idle)
    val viewStateFlow: StateFlow<RepositoriesListViewModelState> = _viewStateFlow.asStateFlow()


    // нужна ли эта инициализация
    init {
        getRepositoriesList()
    }

    private fun getRepositoriesList(): List<RepositoriesModel> {
        viewModelScope.launch {
            _listRepositories = repositoryListUseCase.getRepositoriesList(token)
        }
        return _listRepositories
    }

}