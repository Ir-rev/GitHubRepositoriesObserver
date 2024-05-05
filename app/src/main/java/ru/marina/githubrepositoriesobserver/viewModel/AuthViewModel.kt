package ru.marina.githubrepositoriesobserver.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.state.AuthUserTokenViewModelState
import ru.marina.githubrepositoriesobserver.useCase.AuthLoginUseCase
import javax.inject.Inject

const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken

    @Inject
    lateinit var authLoginUseCase: AuthLoginUseCase

    private var authJob: Job? = null

    private val _viewStateFlow: MutableStateFlow<AuthUserTokenViewModelState> =  MutableStateFlow(AuthUserTokenViewModelState.Idle)
    val viewStateFlow: StateFlow<AuthUserTokenViewModelState> = _viewStateFlow.asStateFlow()

    fun tryAuth(token: String) {
        authJob?.cancel()
        authJob = viewModelScope.launch(Dispatchers.IO) {
            _viewStateFlow.emit(AuthUserTokenViewModelState.Loading)
            val login = authLoginUseCase.authLoginUser(token)
            // елси логин пришёл сохраняешь данные в бд и открываешь новый экран через AuthUserTokenViewModelState.Success
            // если не прошёл ставишь ошибку AuthUserTokenViewModelState.Error
        }
    }

    override fun onCleared() {
        authJob?.cancel()
        super.onCleared()
    }
}