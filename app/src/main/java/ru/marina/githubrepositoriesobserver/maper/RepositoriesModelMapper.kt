package ru.marina.githubrepositoriesobserver.maper

import ru.marina.githubrepositoriesobserver.entity.SingInResponseRepositoryBriefInfoEntity
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

class RepositoriesModelMapper(){

    operator fun invoke(model: SingInResponseRepositoryBriefInfoEntity): RepositoriesModel {
        return RepositoriesModel(name = model.name,
            description = model.description,
            kotlinOrJava = model.kotlinOrJava)
    }

}
