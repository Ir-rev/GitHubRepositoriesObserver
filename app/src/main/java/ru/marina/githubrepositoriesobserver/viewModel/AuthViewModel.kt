package ru.marina.githubrepositoriesobserver.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState

@HiltViewModel
class AuthViewModel @Inject constructor() :ViewModel(){
    @Inject
    private val databaseSaveToken: DatabaseSaveToken?= null


// должны быть экшены

    private fun setResultAuthToken(state: RepositoriesListViewModelState){
        viewModelScope.launch {
            when(state){
                is RepositoriesListViewModelState.Error -> TODO()
                RepositoriesListViewModelState.Loading -> TODO()
                is RepositoriesListViewModelState.Success ->

            }
        }
    }

}