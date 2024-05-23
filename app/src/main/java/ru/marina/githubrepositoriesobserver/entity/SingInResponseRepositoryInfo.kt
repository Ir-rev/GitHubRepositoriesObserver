package ru.marina.githubrepositoriesobserver.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SingInResponseRepositoryInfo (
    //название репозитория
    val name: String?,
    val description: String?,
    // активная ссылка
    @SerialName("html_url")
    val htmlUrl: String?,
//    лицензия
    @SerialName("license")
    val license: License?,
    //код лицензии
//    val licenseKey: String?,

    @SerialName("forks_count")
    val forks: Int?,
    @SerialName("watchers_count")
    val watchers: Int?,
    //звездочки
    @SerialName("stargazers_count")
    val starts: Int?

)

@Serializable
class License(
    val name: String?
)