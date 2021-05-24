package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import com.bobryshev.genesistest2.domain.model.ui.toGitRepoEntity
import com.bobryshev.genesistest2.domain.repository.IGitRepoRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SaveViewedGitRepoUseCase @Inject constructor(private val gitRepository: IGitRepoRepository): ISaveViewedGitRepoUseCase {
    override fun invoke(gitRepoUi: GitRepoUi): Completable {
        return gitRepository.saveViewedRepo(gitRepoUi.toGitRepoEntity())
    }
}