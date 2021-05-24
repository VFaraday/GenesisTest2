package com.bobryshev.genesistest2.data.repository.datasource

import com.bobryshev.genesistest2.data.network.GitHubApi
import com.bobryshev.genesistest2.domain.model.network.GitRepo
import com.bobryshev.genesistest2.domain.model.network.GitRepoList
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GitRepoNetworkDataSource @Inject constructor(private val gitRepoApi: GitHubApi): IGitRepoNetworkDataSource {

    private var text: String? = null
    private var page: Int = 1
    private var page2: Int = 2
    private var perPage = 15

    override fun searchGitRepos(text: String?): Observable<List<GitRepo>> {
        page = 1
        page2 = 2
        this.text = text
        return Observable.zip(gitRepoApi.getRepos(text, "stars", page, perPage), gitRepoApi.getRepos(text, "stars", page2, perPage), { first, second ->
            return@zip combineResults(first, second)
        })
    }

    override fun loadNextPage(): Observable<List<GitRepo>> {
        page++
        page2++
        return Observable.zip(gitRepoApi.getRepos(text, "stars", page, perPage), gitRepoApi.getRepos(text, "stars", page2, perPage), { first, second ->
            return@zip combineResults(first, second)
        })
    }

    private fun combineResults(first: GitRepoList, second: GitRepoList): List<GitRepo> {
        val list = mutableListOf<GitRepo>()
        list.addAll(first.items)
        list.addAll(second.items)
        return list
    }
}