package ru.marina.githubrepositoriesobserver.state



sealed class RepositoryInfoViewModelState {

    object Loading : RepositoryInfoViewModelState()

    data class Error(val message: String) : RepositoryInfoViewModelState()

    class Success(
//        var repositoriesModelList: RepositoriesInfoModel
        val itemList: List<RepositoryInfoItem>
    ) : RepositoryInfoViewModelState()
}
sealed interface RepositoryInfoItem{
    data class Link(val link: String): RepositoryInfoItem
    data class Statistic(val star: String, val fork: String, val watchers: String): RepositoryInfoItem
    data class License(val nameLicense: String): RepositoryInfoItem
    data class Description(val description: String): RepositoryInfoItem

}