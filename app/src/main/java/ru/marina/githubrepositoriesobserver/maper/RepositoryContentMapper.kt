package ru.marina.githubrepositoriesobserver.maper

import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryContent
import ru.marina.githubrepositoriesobserver.model.RepositoryContentModel

class RepositoryContentMapper @Inject constructor(){

    operator fun invoke(model: SingInResponseRepositoryContent): RepositoryContentModel{
        return RepositoryContentModel(
            content = model.content
        )
    }
}