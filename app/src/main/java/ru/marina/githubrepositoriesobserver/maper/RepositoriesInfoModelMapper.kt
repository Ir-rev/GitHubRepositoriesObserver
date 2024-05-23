package ru.marina.githubrepositoriesobserver.maper

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryContent
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryInfo
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.model.RepositoryContentModel

class RepositoriesInfoModelMapper @Inject constructor() {
    operator fun invoke(model: SingInResponseRepositoryInfo): RepositoriesInfoModel {
        return RepositoriesInfoModel(
            name = model.name,
            description = model.description,
            htmlUrl = model.htmlUrl,
            license = model.license?.name ?: "",
            forks = model.forks.toString(),
            watchers = model.watchers.toString(),
            stars = model.starts.toString()
        )
    }

}