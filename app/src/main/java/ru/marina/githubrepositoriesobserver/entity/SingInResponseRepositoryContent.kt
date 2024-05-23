package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SingInResponseRepositoryContent (
    @SerialName("content")
    val content: String?
)