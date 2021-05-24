package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.LoadAction
import com.bobryshev.genesistest2.domain.model.network.GitRepo
import com.bobryshev.genesistest2.domain.model.network.toGitRepoUi
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.bobryshev.genesistest2.domain.repository.IGitRepoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchGitReposUseCase @Inject constructor(private val gitRepoRepository: IGitRepoRepository): ISearchGitReposUseCase {
    override fun invoke(text: String?, loadAction: LoadAction): Observable<List<GitRepoUi>> {
        return when (loadAction) {
            LoadAction.INIT -> gitRepoRepository.searchGitRepos(text).map { map(it) }
            LoadAction.NEXT_PAGE -> gitRepoRepository.loadNextPage().map { map(it) }
        }
    }

    private fun map(items: List<GitRepo>): List<GitRepoUi> {
        return items.map { it.toGitRepoUi() }
    }
}