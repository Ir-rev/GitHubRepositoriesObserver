package ru.marina.githubrepositoriesobserver.state

sealed class AuthUserTokenViewModelState {

    object Idle: AuthUserTokenViewModelState()

    // загрузка логина
    object Loading : AuthUserTokenViewModelState()

    // ошибка логина
    data class Error(val message: String) : AuthUserTokenViewModelState()

    // успех

    class Success(val token: String) : AuthUserTokenViewModelState()
}