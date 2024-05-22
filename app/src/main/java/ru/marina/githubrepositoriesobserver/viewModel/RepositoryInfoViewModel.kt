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
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoItem
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryInfoUseCase

class RepositoryInfoViewModel(
    private val owner: String,
    private val name: String,
    private val useCase: RepositoryInfoUseCase,
    private val databaseSaveToken: DatabaseSaveToken,
) : ViewModel() {

    private val _viewStateFlow: MutableStateFlow<RepositoryInfoViewModelState> =
        MutableStateFlow(RepositoryInfoViewModelState.Loading)
    val viewStateFlow: StateFlow<RepositoryInfoViewModelState> = _viewStateFlow.asStateFlow()

    fun updateRepositoryInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _viewStateFlow.emit(RepositoryInfoViewModelState.Error(throwable.localizedMessage!!))
            }
        }) {

            val model = useCase.getInfoRepository(databaseSaveToken.getToken(), name, owner)

            _viewStateFlow.emit(RepositoryInfoViewModelState.Loading)
            _viewStateFlow.emit(
                RepositoryInfoViewModelState
                    .Success(
                        itemList = listOf(
                            RepositoryInfoItem.Link(model.htmlUrl),
                            RepositoryInfoItem.License(model.licenseKey),
                            RepositoryInfoItem.Statistic(
                                model.stars,
                                model.forks,
                                model.watchers
                            ),
                            RepositoryInfoItem.Description(model.description)
                        )
                    )
            )
        }
    }
}

