package ru.marina.githubrepositoriesobserver.model

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
data class RepositoriesModel (

    var name: String?=null,
    var description: String?=null,
    var kotlinOrJava: String?=null)

