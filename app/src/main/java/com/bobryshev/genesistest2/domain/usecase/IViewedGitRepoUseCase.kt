package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import io.reactivex.rxjava3.core.Observable

interface IViewedGitRepoUseCase {
    operator fun invoke(): Observable<List<GitRepoUi>>
}