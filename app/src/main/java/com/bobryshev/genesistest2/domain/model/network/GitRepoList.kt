package com.bobryshev.genesistest2.domain.model.network

import com.google.gson.annotations.SerializedName

data class GitRepoList(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<GitRepo>
)