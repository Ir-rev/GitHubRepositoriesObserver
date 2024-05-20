package ru.marina.githubrepositoriesobserver.maper

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryInfo
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel

class RepositoriesInfoModelMapper @Inject constructor() {
    operator fun invoke(model: SingInResponseRepositoryInfo): RepositoriesInfoModel {
        return RepositoriesInfoModel(
            name = model.name,
            description = model.description,
            htmlUrl = model.htmlUrl,
            license = model.license,
            licenseKey = model.licenseKey,
            forks = model.forks,
            watchers = model.watchers,
            stars = model.openIssues
        )
    }
}