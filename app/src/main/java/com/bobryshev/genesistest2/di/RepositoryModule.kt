package com.bobryshev.genesistest2.di

import com.bobryshev.genesistest2.data.repository.GitRepoRepository
import com.bobryshev.genesistest2.data.repository.datasource.GitRepoNetworkDataSource
import com.bobryshev.genesistest2.data.repository.datasource.IGitRepoNetworkDataSource
import com.bobryshev.genesistest2.domain.repository.IGitRepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGitRepoRepository(gitRepoRepository: GitRepoRepository): IGitRepoRepository

    @Binds
    @ViewModelScoped
    abstract fun bindGitRepoNetworkDataSource(gitRoNetworkDataSource: GitRepoNetworkDataSource): IGitRepoNetworkDataSource
}