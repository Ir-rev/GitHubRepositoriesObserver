package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.Serializable

@Serializable
class SingInResponseRepositoryInfo (
    //название репозитория
    val name: String?,
    val description: String?,
    // активная ссылка
    val htmlUrl: String?,
    //лицензия
//    val license: String?,
    //код лицензии
    val licenseKey: String?,
    val forks: Int?,
    val watchers: Int?,
    //звездочки
    val openIssues: Int?

)