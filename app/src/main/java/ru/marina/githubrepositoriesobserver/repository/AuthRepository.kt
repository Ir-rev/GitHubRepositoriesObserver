package ru.marina.githubrepositoriesobserver.repository

import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.marina.githubrepositoriesobserver.model.AuthResponseModel

@AndroidEntryPoint
@Singleton
class AuthRepository @Inject constructor() {
    private var repositoriesListParseJson= listOf<AuthResponseModel>()



    private suspend fun getFullRepositoriesList(): List<AuthResponseModel>{
        if (repositoriesListParseJson.isEmpty()){
            repositoriesListParseJson=

        }
    }

//    private val retrofit = Retrofit
//        .Builder()
//        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//        // вставить свою ссылку на репы
//        .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/").build()


}