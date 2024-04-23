package ru.marina.githubrepositoriesobserver.model

import com.google.gson.annotations.SerializedName

data class RepositoriesPermissionsModel(
    @SerializedName("admin") var admin: Boolean? = null,
    @SerializedName("maintain") var maintain: Boolean? = null,
    @SerializedName("push") var push: Boolean? = null,
    @SerializedName("triage") var triage: Boolean? = null,
    @SerializedName("pull") var pull: Boolean? = null
)