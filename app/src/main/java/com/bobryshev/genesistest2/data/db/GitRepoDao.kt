package com.bobryshev.genesistest2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.bobryshev.genesistest2.domain.model.db.GitRepoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface GitRepoDao {

    @Query("SELECT * FROM gitrepoentity")
    fun getViewedRepos(): Observable<List<GitRepoEntity>>

    @Query("SELECT * FROM gitrepoentity")
    fun getViewedRepo(): List<GitRepoEntity>

    @Insert
    fun insert(gitRepoEntity: GitRepoEntity): Completable

    @Insert
    fun insertAll(gitRepoEntity: List<GitRepoEntity>): Completable

    @Transaction
    fun insertRepos(gitRepoEntity: GitRepoEntity): Completable {
        val repos = getViewedRepo().toMutableList()
        if (repos.size >= 20) {
            repos.removeAt(20)
        }
        repos.add(0, gitRepoEntity)
        return insertAll(repos)
    }
}