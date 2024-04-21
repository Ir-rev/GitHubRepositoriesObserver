package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState

@HiltViewModel
class RepositoriesListViewModel @Inject constructor() : ViewModel() {

    private val _repositoriesList = MutableLiveData<RepositoriesListViewModelState>()
    val repositoriesList: LiveData<RepositoriesListViewModelState> = _repositoriesList
}