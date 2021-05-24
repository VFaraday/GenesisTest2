package com.bobryshev.genesistest2.domain.model.network

import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.google.gson.annotations.SerializedName

data class GitRepo(
    val id: String,
    @SerializedName("html_url")
    val htmlUrl: String?,
    val name: String?,
    val description: String?,
    @SerializedName("stargazers_count")
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

        fun build() = GitRepo(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun GitRepo.toGitRepoUi(): GitRepoUi {
    val gitRepo = this
    return GitRepoUi.build {
        id = gitRepo.id
        htmlUrl = gitRepo.htmlUrl
        name = gitRepo.name
        description = gitRepo.description
    }
}