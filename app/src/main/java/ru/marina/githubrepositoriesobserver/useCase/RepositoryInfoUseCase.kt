package ru.marina.githubrepositoriesobserver.useCase

import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlinx.coroutines.CoroutineStart
import ru.marina.githubrepositoriesobserver.maper.RepositoriesInfoModelMapper
import ru.marina.githubrepositoriesobserver.maper.RepositoryContentMapper
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.model.RepositoryContentModel
import ru.marina.githubrepositoriesobserver.repository.RepositoryInfoRepository

class RepositoryInfoUseCase @Inject constructor() {
    @Inject
    lateinit var repository: RepositoryInfoRepository

    @Inject
    lateinit var mapper: RepositoriesInfoModelMapper
    @Inject
    lateinit var mapperContent: RepositoryContentMapper

    suspend fun getInfoRepository(token: String, name: String, owner: String): RepositoriesInfoModel {

        return mapper.invoke(repository.getRepositoryInfo(token = token, repo = name, owner = owner))

    }
    suspend fun getRepositoryContext(token: String, name: String, owner: String): RepositoryContentModel{
        return mapperContent.invoke(repository.getRepositoryContent(token = token, repo = name, owner = owner))
    }
//    internal fun String.toMarkdown(): String {
//        val data = Base64.decode(this, CoroutineStart.DEFAULT)
//        return String(data, StandardCharsets.UTF_8)
////    }
//    fun String.decode(): String{
//    return Base64.decode(this, CoroutineStart.DEFAULT).toString(charset("UTF-8"))
//
//    }


}