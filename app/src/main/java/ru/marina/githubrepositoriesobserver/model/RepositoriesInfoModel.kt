package ru.marina.githubrepositoriesobserver.model

data class RepositoriesInfoModel (
    //название репозитория
    val name: String?,
    val description: String?,
    // активная ссылка
    val htmlUrl: String?,
    //лицензия
    val license: String?,
    //код лицензии
    val licenseKey: String?,
    val forks: String?,
    val watchers: String?,
    //звездочки
    val stars: String?
)
