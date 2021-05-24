package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.model.db.toGitRepoUi
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.bobryshev.genesistest2.domain.repository.IGitRepoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ViewedGitRepoUseCase @Inject constructor(private val gitRepoRepository: IGitRepoRepository): IViewedGitRepoUseCase {
    override fun invoke(): Observable<List<GitRepoUi>> = gitRepoRepository.getViewedGitRepos().map { items -> items.map { it.toGitRepoUi() }  }
}