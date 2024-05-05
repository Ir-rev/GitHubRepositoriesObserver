package ru.marina.githubrepositoriesobserver.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.useCase.AuthLoginUseCase

const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    @Inject
    private val databaseSaveToken: DatabaseSaveToken? = null

    @Inject
    private val authLoginUseCase: AuthLoginUseCase? = null


// должны быть экшены


    private fun setCurrentToken(token: String) {
        databaseSaveToken?.setToken(token)
    }

    private fun getUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseSaveToken?.getToken()
        }
    }


}