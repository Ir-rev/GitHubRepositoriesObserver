package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.Serializable

@Serializable
class SingInResponseRepositoryBriefInfoEntity (
    val name: String?,
//    val login: String?,
    val description: String?,
    val language: String?
)