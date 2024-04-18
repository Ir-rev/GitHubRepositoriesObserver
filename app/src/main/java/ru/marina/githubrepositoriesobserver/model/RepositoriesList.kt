package ru.marina.githubrepositoriesobserver.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class RepositoriesList @Inject constructor(
    @SerializedName("repositories") var repositoriesList: List<AuthResponseModel> = arrayListOf()
)