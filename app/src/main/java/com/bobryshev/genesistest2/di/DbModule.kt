package com.bobryshev.genesistest2.di

import android.content.Context
import androidx.room.Room
import com.bobryshev.genesistest2.data.db.AppDataBase
import com.bobryshev.genesistest2.data.db.GitRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "GitRepo").build()
    }

    @Provides
    @Singleton
    fun provideGitRepoDao(appDataBase: AppDataBase): GitRepoDao {
        return appDataBase.getGitRepoDap()
    }
}