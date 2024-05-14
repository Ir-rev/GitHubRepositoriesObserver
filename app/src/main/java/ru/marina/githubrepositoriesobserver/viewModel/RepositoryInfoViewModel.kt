package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryInfoUseCase

@HiltViewModel
class RepositoryInfoViewModel @Inject constructor(): ViewModel(){

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken
    @Inject
    lateinit var useCase: RepositoryInfoUseCase

    private val _viewStateFlow: MutableStateFlow<RepositoryInfoViewModelState> =
        MutableStateFlow(RepositoryInfoViewModelState.Loading)
    val viewStateFlow: StateFlow<RepositoryInfoViewModelState> = _viewStateFlow.asStateFlow()

    fun updateRepositoryInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _viewStateFlow.emit(RepositoryInfoViewModelState.Error(throwable.localizedMessage))
            }
        }) {
            _viewStateFlow.emit(RepositoryInfoViewModelState.Loading)
            _viewStateFlow.emit(
                RepositoryInfoViewModelState
                    .Success(useCase.getInfoRepository(databaseSaveToken.getToken()))
            )
        }
    }
}

