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

@HiltViewModel
class RepositoryInfoViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken

    @Inject
    lateinit var useCase: RepositoryInfoUseCase

    private val _viewStateFlow: MutableStateFlow<RepositoryInfoViewModelState> =
        MutableStateFlow(RepositoryInfoViewModelState.Loading)
    val viewStateFlow: StateFlow<RepositoryInfoViewModelState> = _viewStateFlow.asStateFlow()


    init {
        updateRepositoryInfo()
    }

    private fun updateRepositoryInfo() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _viewStateFlow.emit(RepositoryInfoViewModelState.Error(throwable.localizedMessage.toString()))
            }
        }) {
//            var model: RepositoriesInfoModel = useCase.getInfoRepository(databaseSaveToken.getToken())

            _viewStateFlow.emit(RepositoryInfoViewModelState.Loading)
            _viewStateFlow.emit(
                RepositoryInfoViewModelState
                    .Success(
                        itemList = listOf(
                            RepositoryInfoItem.Link("https://kmm.icerock.dev/university/android-basics/practice#функциональные-требования"),
                            RepositoryInfoItem.License("AAAAAAhhhjijiojihygygfs#"),
                            RepositoryInfoItem.Statistic("1000", "45", "99999"),
                            RepositoryInfoItem.Description("ygyecgfywdgycgergfche38uwfgc7gterw67tf6te7d78yce87tct6xrfew6rdc67t273dyc8xgywsfctfxqwtd7ctq782eywc89uqe9w0qiqcv9oudfuhyv c7yqewd78ct")
                        )
                    )
            )
        }
    }
}

