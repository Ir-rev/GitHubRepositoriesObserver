package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryListUseCase

@HiltViewModel
class RepositoriesListViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repositoryListUseCase: RepositoryListUseCase

    private val _repositoriesList = MutableLiveData<RepositoriesListViewModelState>()
    val repositoriesList: LiveData<RepositoriesListViewModelState> = _repositoriesList

    private fun getRepositoriesList(token: String){
        viewModelScope.launch {
            repositoryListUseCase.getRepositoriesList(token)
        }

    }
}