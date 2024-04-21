package ru.marina.githubrepositoriesobserver.model

import com.google.gson.annotations.SerializedName
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

data class AuthResponsePlanModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("space") var space: Int? = null,
    @SerializedName("collaborators") var collaborators: Int? = null,
    @SerializedName("private_repos") var privateRepos: Int? = null
)