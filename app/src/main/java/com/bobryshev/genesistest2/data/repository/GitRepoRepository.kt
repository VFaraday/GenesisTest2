package com.bobryshev.genesistest2.data.repository

import com.bobryshev.genesistest2.data.db.GitRepoDao
import com.bobryshev.genesistest2.data.network.GitHubApi
import com.bobryshev.genesistest2.data.repository.datasource.IGitRepoNetworkDataSource
import com.bobryshev.genesistest2.domain.model.db.GitRepoEntity
import com.bobryshev.genesistest2.domain.model.network.GitRepo
import com.bobryshev.genesistest2.domain.repository.IGitRepoRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GitRepoRepository @Inject constructor(private val gitRepoNetworkDataSource: IGitRepoNetworkDataSource,
                                            private val gitRepoDao: GitRepoDao): IGitRepoRepository {

    override fun searchGitRepos(text: String?): Observable<List<GitRepo>> {
        return gitRepoNetworkDataSource.searchGitRepos(text)
    }

    override fun loadNextPage(): Observable<List<GitRepo>> {
        return gitRepoNetworkDataSource.loadNextPage()
    }

    override fun saveViewedRepo(gitRepoEntity: GitRepoEntity): Completable {
        return gitRepoDao.insertRepos(gitRepoEntity)
    }

    override fun getViewedGitRepos(): Observable<List<GitRepoEntity>> {
        return gitRepoDao.getViewedRepos()
    }
}