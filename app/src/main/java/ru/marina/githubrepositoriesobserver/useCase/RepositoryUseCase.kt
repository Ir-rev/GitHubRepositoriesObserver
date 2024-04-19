package ru.marina.githubrepositoriesobserver.useCase

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel
import ru.marina.githubrepositoriesobserver.repository.AuthRepository

@AndroidEntryPoint
class RepositoryUseCase {

    @Inject
    val authRepository= AuthRepository().providerRetrofit()

    //логин
    private suspend fun getLoginUser(): String?{
       return getAllInfoRepositories().map { list-> list.login }.first()
    }

    //список всей инфо по репозиториям
    private suspend fun getAllInfoRepositories(): List<AuthResponseModel>{
       return authRepository.getAllInfoRepositoryList().repositoriesList
    }

    // список репозиториев
    private suspend fun getRepositoriesList(): List<String?>{
        return getAllInfoRepositories().map { list-> list.reposUrl }
    }

    // ЯП
}