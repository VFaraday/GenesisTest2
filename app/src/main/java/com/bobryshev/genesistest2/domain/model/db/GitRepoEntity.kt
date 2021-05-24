package com.bobryshev.genesistest2.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi

@Entity
class GitRepoEntity(
    @PrimaryKey
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

        fun build() = GitRepoEntity(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun GitRepoEntity.toGitRepoUi(): GitRepoUi {
    val gitRepo = this
    return GitRepoUi.build {
        id = gitRepo.id
        htmlUrl = gitRepo.htmlUrl
        name = gitRepo.name
        description = gitRepo.description
    }
}