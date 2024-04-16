package ru.marina.githubrepositoriesobserver.repository

import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@AndroidEntryPoint
class AuthRepository private constructor() {

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        // вставить свою ссылку на репы
        .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/").build()


}