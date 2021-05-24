package com.bobryshev.genesistest2.di

import com.bobryshev.genesistest2.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSearchGitReposUseCase(searchGitReposUseCase: SearchGitReposUseCase): ISearchGitReposUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindViewedGitReposUseCase(viewedGitRepoUseCase: ViewedGitRepoUseCase): IViewedGitRepoUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSaveGitRepoUseCase(saveViewedGitRepoUseCase: SaveViewedGitRepoUseCase): ISaveViewedGitRepoUseCase
}