package com.bobryshev.genesistest2.data.network

import com.bobryshev.genesistest2.domain.model.network.GitRepoList
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepos(@Query("q") q: String?,
                 @Query("sort") sort: String,
                 @Query("page") page: Int,
                 @Query("per_page") perPage: Int): Observable<GitRepoList>
}