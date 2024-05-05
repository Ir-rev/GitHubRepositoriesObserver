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
import ru.marina.githubrepositoriesobserver.state.AuthUserTokenViewModelState
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.useCase.AuthLoginUseCase

const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    @Inject
    private val databaseSaveToken: DatabaseSaveToken? = null

    @Inject
    private val authLoginUseCase: AuthLoginUseCase? = null



    fun setResultAuthToken(state: AuthUserTokenViewModelState, context: Context, token: String){
        viewModelScope.launch {
            when(state){
                is AuthUserTokenViewModelState.Error -> Toast.makeText(context, "Неверный токен!", Toast.LENGTH_SHORT).show()
                is AuthUserTokenViewModelState.Loading -> Toast.makeText(context, "Загрузка...", Toast.LENGTH_SHORT).show()
                is AuthUserTokenViewModelState.Success -> authLoginUseCase?.authLoginUser(token)

            }
        }

    }


    fun setCurrentToken(token: String) {
        databaseSaveToken?.setToken(token)
    }

    fun getUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseSaveToken?.getToken()
        }
    }


}