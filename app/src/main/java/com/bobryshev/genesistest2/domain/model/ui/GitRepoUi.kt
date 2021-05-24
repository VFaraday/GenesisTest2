package com.bobryshev.genesistest2.domain.model.ui

import com.bobryshev.genesistest2.domain.model.db.GitRepoEntity
import com.bobryshev.genesistest2.domain.model.network.GitRepo

data class GitRepoUi(
    val id: String,
    val htmlUrl: String?,
    val name: String?,
    val description: String?,
    val stars: Int?,
) {

    private constructor(builder: Builder): this(builder.id, builder.htmlUrl, builder.name, builder.description,
        builder.stars, )

    class Builder {
        var id: String = ""
        var htmlUrl: String? = null
        var name: String? = null
        var description: String? = null
        var stars: Int? = null

        fun build() = GitRepoUi(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun GitRepoUi.toGitRepo(): GitRepo {
    val gitRepoUi = this
    return GitRepo.build {
        id = gitRepoUi.id
        htmlUrl = gitRepoUi.htmlUrl
        name = gitRepoUi.name
        description = gitRepoUi.description
    }
}

fun GitRepoUi.toGitRepoEntity(): GitRepoEntity {
    val gitRepoUi = this
    return GitRepoEntity.build {
        id = gitRepoUi.id
        htmlUrl = gitRepoUi.htmlUrl
        name = gitRepoUi.name
        description = gitRepoUi.description
    }
}