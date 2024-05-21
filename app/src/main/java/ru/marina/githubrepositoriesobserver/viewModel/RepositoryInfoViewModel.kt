package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoItem
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryInfoUseCase
import ru.marina.githubrepositoriesobserver.useCase.RepositoryListUseCase

@HiltViewModel
class RepositoryInfoViewModel @Inject constructor(
   private val owner: String,
    private val name: String
) : ViewModel() {

    private val _viewStateFlow: MutableStateFlow<RepositoryInfoViewModelState> =
        MutableStateFlow(RepositoryInfoViewModelState.Loading)
    val viewStateFlow: StateFlow<RepositoryInfoViewModelState> = _viewStateFlow.asStateFlow()

    @Inject
    lateinit var useCase: RepositoryInfoUseCase
    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken



    init {
        updateRepositoryInfo()
    }

    private fun updateRepositoryInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _viewStateFlow.emit(RepositoryInfoViewModelState.Error(throwable.localizedMessage.toString()))
            }
        }) {

            val model= useCase.getInfoRepository(databaseSaveToken.getToken(), name, owner)

            _viewStateFlow.emit(RepositoryInfoViewModelState.Loading)
            _viewStateFlow.emit(
                RepositoryInfoViewModelState
                    .Success(
                        itemList = listOf(
                            RepositoryInfoItem.Link(model.htmlUrl.toString()),
                            RepositoryInfoItem.License(model.licenseKey.toString()),
                            RepositoryInfoItem.Statistic(
                                model.stars.toString(),
                                model.forks.toString(),
                                model.watchers.toString()),
                            RepositoryInfoItem.Description(model.description.toString())
                        )
                    )
            )
        }
    }
}

