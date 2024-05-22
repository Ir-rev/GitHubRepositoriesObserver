package ru.marina.githubrepositoriesobserver.useCase

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.repository.AuthUserLoginRepository

class AuthLoginUseCase @Inject constructor(
    private val authRepository: AuthUserLoginRepository
) {
    suspend fun authLoginUser(token: String): String {
        return authRepository.authLoginUser(token)
    }

}