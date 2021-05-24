package com.bobryshev.genesistest2.data.repository.datasource

import com.bobryshev.genesistest2.domain.model.network.GitRepo
import io.reactivex.rxjava3.core.Observable

interface IGitRepoNetworkDataSource {

    fun searchGitRepos(text: String?): Observable<List<GitRepo>>
    fun loadNextPage(): Observable<List<GitRepo>>
}