package ru.marina.githubrepositoriesobserver.model

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
data class RepositoriesModel @Inject constructor(

    var name: String?=null,
    var description: String?=null,
    var kotlinOrJava: String?=null)

