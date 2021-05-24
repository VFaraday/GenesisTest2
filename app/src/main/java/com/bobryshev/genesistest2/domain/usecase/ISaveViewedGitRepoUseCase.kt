package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import io.reactivex.rxjava3.core.Completable

interface ISaveViewedGitRepoUseCase {
    operator fun invoke(gitRepoUi: GitRepoUi): Completable
}