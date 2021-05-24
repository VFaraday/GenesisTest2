package com.bobryshev.genesistest2.domain.usecase

import com.bobryshev.genesistest2.domain.LoadAction
import com.bobryshev.genesistest2.domain.model.ui.GitRepoUi
import io.reactivex.rxjava3.core.Observable

interface ISearchGitReposUseCase {
    operator fun invoke(text: String?, loadAction: LoadAction): Observable<List<GitRepoUi>>
}