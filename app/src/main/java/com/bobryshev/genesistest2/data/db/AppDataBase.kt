package com.bobryshev.genesistest2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bobryshev.genesistest2.domain.model.db.GitRepoEntity

@Database(entities = [GitRepoEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getGitRepoDap(): GitRepoDao
}