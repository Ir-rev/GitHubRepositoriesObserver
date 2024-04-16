package ru.marina.githubrepositoriesobserver.model

import com.google.gson.annotations.SerializedName

data class RepositoriesList (
    @SerializedName("repositories") var repositoriesList: List<AuthResponseModel> = arrayListOf()
)