package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SingInResponseRepositoryBriefInfoEntity(
    val name: String? = null,
//    val owner: String,
    val description: String? = null,
    val language: String? = null,
    @SerialName("owner")
    val owner: Owner? = null
)

@Serializable
class Owner {
    val login: String? = null
}
