package com.bobryshev.genesistest2.domain.repository

import com.bobryshev.genesistest2.domain.model.db.GitRepoEntity
import com.bobryshev.genesistest2.domain.model.network.GitRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface IGitRepoRepository {

    fun searchGitRepos(text: String?): Observable<List<GitRepo>>
    fun loadNextPage(): Observable<List<GitRepo>>
    fun saveViewedRepo(gitRepoEntity: GitRepoEntity): Completable
    fun getViewedGitRepos(): Observable<List<GitRepoEntity>>
}