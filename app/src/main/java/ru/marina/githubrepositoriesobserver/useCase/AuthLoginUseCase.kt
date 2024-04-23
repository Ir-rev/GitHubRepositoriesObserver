package ru.marina.githubrepositoriesobserver.useCase

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.repository.AuthUserLoginRepository

class AuthLoginUseCase @Inject constructor(
    private val authRepository: AuthUserLoginRepository
) {

    //логин
    suspend fun authLoginUser(token: String): String {
        return authRepository.authLoginUser(token)
    }

    // TODO: вынести в отдельный юзкейс
    //список всей инфо по репозиториям
//    private suspend fun getAllInfoRepositories(): List<AuthResponseModel> {
//        return authRepository.getAllInfoUserList().repositoriesList
//    }

    // TODO: вынести в отдельный юзкейс
//    // список репозиториев
//    private suspend fun getRepositoriesList(): List<String?> {
//        return getAllInfoRepositories().map { list -> list.reposUrl }
//    }

    // ЯП
}