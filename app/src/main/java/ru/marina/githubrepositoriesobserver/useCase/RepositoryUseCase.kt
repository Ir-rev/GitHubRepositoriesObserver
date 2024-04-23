package ru.marina.githubrepositoriesobserver.useCase

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel
import ru.marina.githubrepositoriesobserver.repository.AuthRepository

class RepositoryUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    //логин
    suspend fun authUser(token: String): String {
        return authRepository.authUser(token)
    }

    // TODO: вынести в отдельный юзкейс
//    //список всей инфо по репозиториям
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