package ru.marina.githubrepositoriesobserver.repositories

import dagger.hilt.android.AndroidEntryPoint
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel

@AndroidEntryPoint
class AppRepository {

    private suspend fun getFullRepositoriesList(): List<AuthResponseModel>{

    }
}