package ru.marina.githubrepositoriesobserver.viewModel

import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel
import ru.marina.githubrepositoriesobserver.state.AuthUserTokenViewModelState
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryListUseCase

@HiltViewModel
class RepositoriesListViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repositoryListUseCase: RepositoryListUseCase

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken
   // private val token = "1" // TODO\


    private val _viewStateFlow: MutableStateFlow<RepositoriesListViewModelState> =
        MutableStateFlow(RepositoriesListViewModelState.Loading)
    val viewStateFlow: StateFlow<RepositoriesListViewModelState> = _viewStateFlow.asStateFlow()

    fun updateRepositoriesList() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _viewStateFlow.emit(RepositoriesListViewModelState.Error(throwable.localizedMessage))
            }
        }) {
            _viewStateFlow.emit(RepositoriesListViewModelState.Loading)
            _viewStateFlow.emit(
                RepositoriesListViewModelState
                    .Success(repositoryListUseCase.getRepositoriesList(databaseSaveToken.getToken()))
            )
        }
    }

}

