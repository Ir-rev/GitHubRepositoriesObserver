package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.Serializable

@Serializable
class SignInResponseUserLoginEntity(
    val login: String
)